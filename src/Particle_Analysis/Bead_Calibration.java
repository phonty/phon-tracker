package Particle_Analysis;

import Particle.IsoGaussian;
import IAClasses.ProgressDialog;
import IAClasses.Utils;
import Particle.Particle;
import Particle.ParticleArray;
import ParticleTracking.UserVariables;
import Revision.Revision;
import UtilClasses.GenUtils;
import ij.IJ;
import ij.ImagePlus;
import ij.ImageStack;
import ij.gui.GenericDialog;
import ij.plugin.ChannelSplitter;
import ij.plugin.PlugIn;
import ij.process.ByteProcessor;
import ij.process.ColorProcessor;
import ij.process.FloatProcessor;
import ij.process.TypeConverter;
import ij.text.TextWindow;
import java.awt.Font;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Bead_Calibration extends Particle_Tracker implements PlugIn {

    protected ImageStack stacks[];
    protected ImagePlus imp;
//    protected ImageStack[] stacks = new ImageStack[2];
    protected String title = "Colocaliser";
    protected String resultsHeadings = "Image\tChannel 1 Detections\tColocalised Channel 2 Detections\t% Colocalisation\t"
            + "\u0394 (nm)", coordHeadings;
//    protected static double coFactor = 1.0;
    public static final int RED = 0, GREEN = 1, BLUE = 2;
    protected DecimalFormat numFormat = new DecimalFormat("0.0");
//    protected static boolean partialDetect = false;
    protected TextWindow results = null, particleCoords = null;
    protected boolean findTails = false;
//    private final String labels[] = {"Channel 1", "Channel 2"};
    private final DecimalFormat intFormat = new DecimalFormat("000");
    private boolean floatingSigma;
    public static final int HEADER_SIZE = 3;

//    public static void main(String args[]) {
//        ImagePlus inputs[] = new ImagePlus[2];
//        inputs[0] = IJ.openImage();
//        inputs[1] = IJ.openImage();
//        (new Bead_Calibration(inputs)).run(null);
//        System.exit(0);
//    }
    public Bead_Calibration() {
    }

    public Bead_Calibration(ImagePlus[] inputs) {
        ImageStack tempStacks[] = new ImageStack[2];
        tempStacks[0] = inputs[0].getImageStack();
        tempStacks[1] = inputs[1].getImageStack();
        this.inputs = inputs;
        this.stacks = tempStacks;
    }

    public Bead_Calibration(ImagePlus imp) {
        this.imp = imp;
        ImagePlus tempImps[] = ChannelSplitter.split(imp);
        stacks[0] = tempImps[0].getImageStack();
        stacks[1] = tempImps[1].getImageStack();
    }

    @Override
    public void run(String arg) {
        title = title + "_v" + Revision.VERSION + "." + intFormat.format(Revision.revisionNumber);
        labels[0] = "Channel 1";
        labels[1] = "Channel 2";
        if (IJ.getInstance() != null) {
            stacks = new ImageStack[2];
            getActiveImages();
        }
        if (stacks[1] == null) {
            GenUtils.error("Not enough stacks open.");
            return;
        }
//        C0 = stacks[0].getSliceLabel(1);
//        C1 = stacks[1].getSliceLabel(1);
        coordHeadings = "C0_X\tC0_Y\tC1_X\tC1_Y\tC0_\u03c3\tC1_\u03c3\tC0_Fit\tC1_Fit";
        if (showDialog()) {
            UserVariables.setPreProcess(true);
            Particle_Tracker analyser = new Particle_Tracker(stacks);
            analyser.calcParticleRadius(UserVariables.getSpatialRes(), UserVariables.getSigEstRed());
            UserVariables.setnMax(1);
            (buildOutput(analyser)).show();
        }
    }

    public boolean showDialog() {
        if (stacks == null) {
            Toolkit.getDefaultToolkit().beep();
            IJ.error("No images open.");
            return false;
        }
        boolean valid = false;
        while (!valid) {
            valid = true;
            GenericDialog dialog = new GenericDialog(title, IJ.getInstance());
            dialog.addMessage("Channel 2 will be co-localised with channel 1", new Font("Helvetica", Font.BOLD, 12));
            dialog.addMessage("Channel 1: " + inputs[0].getTitle());
            dialog.addMessage("Channel 2: " + inputs[1].getTitle());
            dialog.addMessage("");
            dialog.addNumericField("Spatial Resolution:", UserVariables.getSpatialRes() * 1000.0, 3, 7, "nm/pixel");
            dialog.addNumericField("Minimum Peak Size (Ch 1):", UserVariables.getChan1MaxThresh(), 3, 7, "");
            dialog.addNumericField("Minimum Peak Size (Ch 2):", UserVariables.getChan2MaxThresh(), 3, 7, "");
            dialog.addNumericField("Curve Fit Tolerance:", UserVariables.getCurveFitTol(), 3, 7, "");
//            dialog.addNumericField("Colocalisation Factor:", coFactor, 3, 7, "");
            dialog.addCheckbox("Floating Sigma", floatingSigma);
//            dialog.addCheckbox("Include Partial Detections", partialDetect);
            dialog.showDialog();
            if (!dialog.wasCanceled()) {
//                UserVariables.setC1Index(0);
//                UserVariables.setC2Index(1);
                UserVariables.setSpatialRes(dialog.getNextNumber() / 1000.0);
                UserVariables.setChan1MaxThresh(dialog.getNextNumber());
                UserVariables.setChan2MaxThresh(dialog.getNextNumber());
                UserVariables.setCurveFitTol(dialog.getNextNumber());
                /*
                 * Timelapse_Analysis.setC1SigmaTol(sigmaTolC1);
                 * Timelapse_Analysis.setC2SigmaTol(sigmaTolC2);
                 */
//                coFactor = dialog.getNextNumber();
//                partialDetect = dialog.getNextBoolean();
                floatingSigma = dialog.getNextBoolean();
                // Check that entries were numeric:
                if (dialog.invalidNumber()) {
                    Toolkit.getDefaultToolkit().beep();
                    IJ.error("Entries must be numeric!");
                    valid = false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

//    byte[] outPix(ImageProcessor ch1, ImageProcessor ch2, int colour) {
//        ImageProcessor fch1 = (new TypeConverter(ch1, true)).convertToByte();
//        ImageProcessor fch2 = (new TypeConverter(ch2, true)).convertToByte();
//        if (fch1 == null || fch2 == null) {
//            return null;
//        } else if (UserVariables.getC1Index() == colour) {
//            return (byte[]) fch1.getPixels();
//        } else if (UserVariables.getC2Index() == colour) {
//            return (byte[]) fch2.getPixels();
//        } else {
//            return (byte[]) (new ByteProcessor(fch1.getWidth(), fch1.getHeight())).getPixels();
//        }
//    }
    ImagePlus buildOutput(Particle_Tracker analyser) {
        if (stacks == null) {
            return null;
        }
        if (analyser == null) {
            analyser = new Particle_Tracker(stacks);
        }
//        double displaymax = 0.0;
        int colocalisation, count;
        int width = stacks[0].getWidth(), height = stacks[0].getHeight();
        ImageStack outStack = new ImageStack(width, height);
//        double res = UserVariables.getSpatialRes();
        double sepsum;
        ProgressDialog progress = new ProgressDialog(null, "Analysing Stacks...", false, title, false);
        progress.setVisible(true);
        for (int i = 0; i < stacks[0].getSize(); i++) {
            progress.updateProgress(i, stacks[0].getSize());
            colocalisation = 0;
            count = 0;
            sepsum = 0.0;
            ParticleArray curves = analyser.findParticles(false, i, i,
                    UserVariables.getCurveFitTol(), stacks[0], stacks[1], true, floatingSigma, true, UserVariables.isBlobs());
            FloatProcessor ch1proc = new FloatProcessor(width, height);
            FloatProcessor ch2proc = new FloatProcessor(width, height);
            ArrayList<Particle> detections = curves.getLevel(0);
//            String fits;
            for (int j = 0; j < detections.size(); j++) {
                if (detections.get(j) instanceof IsoGaussian) {
                    IsoGaussian c1 = (IsoGaussian) detections.get(j);
                    String coordString;
                    if (particleCoords == null) {
                        particleCoords = new TextWindow(title + " Particle Coordinates", coordHeadings, new String(), 1000, 500);
                        particleCoords.append("WIDTH\t" + (width * UserVariables.getSpatialRes()));
                        particleCoords.append("HEIGHT\t" + (height * UserVariables.getSpatialRes()));
                    }
                    if (Utils.draw2DGaussian(ch1proc, c1, UserVariables.getCurveFitTol(), UserVariables.getSpatialRes(), false)) {
//                    if (c1.getMagnitude() > displaymax) {
//                        displaymax = c1.getMagnitude();
//                    }
                        count++;
                        IsoGaussian c2 = (IsoGaussian)c1.getColocalisedParticle();
                        if (Utils.draw2DGaussian(ch2proc, c2, UserVariables.getCurveFitTol(), UserVariables.getSpatialRes(), false)) {
//                        if (c2.getMagnitude() > displaymax) {
//                            displaymax = c2.getMagnitude();
//                        }
                            colocalisation++;
                            sepsum += Utils.calcDistance(c1.getX(), c1.getY(), c2.getX(), c2.getY());
                            coordString = String.valueOf(c1.getX()) + "\t" + String.valueOf(c1.getY())
                                    + "\t" + String.valueOf(c2.getX()) + "\t" + String.valueOf(c2.getY())
                                    + "\t" + String.valueOf(c1.getXSigma() * UserVariables.getSpatialRes())
                                    + "\t" + String.valueOf(c2.getXSigma() * UserVariables.getSpatialRes())
                                    + "\t" + String.valueOf(c1.getFit()) + "\t" + String.valueOf(c2.getFit());
                            particleCoords.append(coordString);
//                    } else {
//                        coordString = String.valueOf(c1.getX()) + "\t" + String.valueOf(c1.getY())
//                                + "\t \t \t" + String.valueOf(c1.getXSigma()) + "\t \t"
//                                + String.valueOf(c1.getFit());
                        }
                    }
                }
            }
            if (results == null) {
                results = new TextWindow(title + " Results", resultsHeadings, new String(), 1000, 500);
            }
            results.append("Slice " + i + "\t" + count + "\t" + colocalisation
                    + "\t" + numFormat.format(100.0 * colocalisation / count)
                    + "\t" + numFormat.format(1000.0 * sepsum / count));

            ColorProcessor output = new ColorProcessor(width, height);
            output.setRGB((byte[]) (new TypeConverter(ch1proc, false)).convertToByte().getPixels(),
                    (byte[]) (new TypeConverter(ch2proc, false)).convertToByte().getPixels(),
                    (byte[]) (new ByteProcessor(width, height)).getPixels());
            outStack.addSlice("" + i, output);
        }
        progress.dispose();
        if (results != null) {
            results.append("\n" + toString());
            results.setVisible(true);
        }
        if (particleCoords != null) {
            particleCoords.setVisible(true);
        }
        ImagePlus output = new ImagePlus("Detected Particles", outStack);
//        IJ.saveAs(output, "TIF", "c:/users/barry05/desktop/output.tif");
//        if (displaymax > 255) {
//            displaymax = 255;
//        }
//        output.setDisplayRange(0.0, displaymax);
        return output;
    }
}
