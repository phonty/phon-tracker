/* 
 * Copyright (C) 2014 David Barry <david.barry at cancer.org.uk>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ui;

import IAClasses.IsoGaussian;
import IAClasses.Utils;
import ParticleTracking.Analyse_;
import ParticleTracking.GPU_Analyse;
import ParticleTracking.Particle;
import ParticleTracking.ParticleArray;
import ParticleTracking.UserVariables;
import UIClasses.UIMethods;
import ij.IJ;
import ij.ImagePlus;
import ij.ImageStack;
import ij.gui.ImageCanvas;
import ij.process.ImageProcessor;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.DefaultBoundedRangeModel;

public class UserInterface extends javax.swing.JDialog {

    private final Analyse_ analyser;
    private final ImagePlus imp;
    private final String title;
    private boolean wasOKed = false, monoChrome;
    private static final String channel1LabelText = "Channel 1:";
    private static final String channel2LabelText = "Channel 2:";
    private static final String spatResLabelText = "Spatial resolution (" + IJ.micronSymbol + "m/pixel):";
    private static final String fpsLabelText = "Frames per second:";
    private static final String minTrajLengthLabelText = "Minimum trajectory length (s):";
    private static final String minTrajDistLabelText = "Minimum trajectory distance (" + IJ.micronSymbol + "m):";
    private static final String minTrajMSDLabelText = "Minimum trajectory MSD (" + IJ.micronSymbol + "m^2/s):";
    private static final String maxLinkDistLabelText = "Maximum linking distance:";
    private static final String chan1MaxThreshLabelText = "Minimum peak size:";
//    private static final String chan2MaxThreshLabelText = "Minimum peak size (C2):";
    private static final String c1CurveFitTolLabelText = "Curve fit tolerance:";
//    private static final String c2CurveFitTolLabelText = "Curve fit tolerance (C2):";
    private static final String colocalToggleText = "Co-Localised Only";
    private static final String preprocessToggleText = "Pre-Process Images";
    private static final String gpuToggleText = "Use GPU";
    private static final String useCalToggleText = "Use Spatial Calibration";
    private static final String extractSigsToggleText = "Extract Fluorescence Profiles";
    private static final String trackLengthText = "Track Length (" + IJ.micronSymbol + "m):";
    private static final String prevResToggleText = "Preview Results";
    private static final String colocalThreshText = "Colocalisation Threshold:";

    /**
     * Creates new form UserInterface
     */
    public UserInterface(java.awt.Frame parent, boolean modal, String title, Analyse_ analyser) {
        super(parent, modal);
        this.title = title;
        this.analyser = analyser;
        ImageStack[] stacks = analyser.getStacks();
        this.monoChrome = (stacks[1] == null);
        this.imp = new ImagePlus("", Utils.updateImage(stacks[0], stacks[1], 1));
        if (monoChrome) {
            UserVariables.setColocal(!monoChrome);
        }
        initComponents();
        UIMethods.centreDialog(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        c1Label = new javax.swing.JLabel();
        c1ComboBox = new javax.swing.JComboBox();
        spatResLabel = new javax.swing.JLabel();
        timeResLabel = new javax.swing.JLabel();
        minTrajLengthLabel = new javax.swing.JLabel();
        maxTrajStepLabel = new javax.swing.JLabel();
        chan1MaxThreshLabel = new javax.swing.JLabel();
        spatResTextField = new javax.swing.JTextField();
        timeResTextField = new javax.swing.JTextField();
        minTrajLengthTextField = new javax.swing.JTextField();
        maxTrajStepTextField = new javax.swing.JTextField();
        chan1MaxThreshTextField = new javax.swing.JTextField();
        preprocessToggleButton = new javax.swing.JToggleButton();
        c1CurveFitTolLabel = new javax.swing.JLabel();
        c1CurveFitTolTextField = new javax.swing.JTextField();
        gpuToggleButton = new javax.swing.JToggleButton();
        minTrajDistLabel = new javax.swing.JLabel();
        minTrajDistTextField = new javax.swing.JTextField();
        trackLengthLabel = new javax.swing.JLabel();
        trackLengthTextField = new javax.swing.JTextField();
        previewResultsToggleButton = new javax.swing.JToggleButton();
        useCalsToggleButton = new javax.swing.JToggleButton();
        extractSigsToggleButton = new javax.swing.JToggleButton();
        minTrajMSDLabel = new javax.swing.JLabel();
        minTrajMSDTextField = new javax.swing.JTextField();
        colocalToggleButton = new javax.swing.JToggleButton();
        colocalThreshLabel = new javax.swing.JLabel();
        colocalThreshTextField = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        canvas1 = new ImageCanvas(imp);
        previewScrollBar = new javax.swing.JScrollBar();
        previewTextField = new javax.swing.JTextField();
        previewToggleButton = new javax.swing.JToggleButton();
        jPanel3 = new javax.swing.JPanel();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(title);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Channel 2 will be co-localised with channel1:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        jPanel1.add(jLabel1, gridBagConstraints);

        c1Label.setText(channel1LabelText);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanel1.add(c1Label, gridBagConstraints);

        c1ComboBox.setModel(new javax.swing.DefaultComboBoxModel(UserVariables.channels));
        c1ComboBox.setSelectedIndex(UserVariables.getC1Index());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(c1ComboBox, gridBagConstraints);

        spatResLabel.setText(spatResLabelText);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanel1.add(spatResLabel, gridBagConstraints);

        timeResLabel.setText(fpsLabelText);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanel1.add(timeResLabel, gridBagConstraints);

        minTrajLengthLabel.setText(minTrajLengthLabelText);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanel1.add(minTrajLengthLabel, gridBagConstraints);

        maxTrajStepLabel.setText(maxLinkDistLabelText);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanel1.add(maxTrajStepLabel, gridBagConstraints);

        chan1MaxThreshLabel.setText(chan1MaxThreshLabelText);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanel1.add(chan1MaxThreshLabel, gridBagConstraints);

        spatResTextField.setText(String.valueOf(UserVariables.getSpatialRes()));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(spatResTextField, gridBagConstraints);

        timeResTextField.setText(String.valueOf(UserVariables.getTimeRes()));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(timeResTextField, gridBagConstraints);

        minTrajLengthTextField.setText(String.valueOf(UserVariables.getMinTrajLength()));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(minTrajLengthTextField, gridBagConstraints);

        maxTrajStepTextField.setText(String.valueOf(UserVariables.getTrajMaxStep()));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(maxTrajStepTextField, gridBagConstraints);

        chan1MaxThreshTextField.setText(String.valueOf(UserVariables.getChan1MaxThresh()));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(chan1MaxThreshTextField, gridBagConstraints);

        preprocessToggleButton.setText(preprocessToggleText);
        preprocessToggleButton.setSelected(UserVariables.isPreProcess());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanel1.add(preprocessToggleButton, gridBagConstraints);

        c1CurveFitTolLabel.setText(c1CurveFitTolLabelText);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanel1.add(c1CurveFitTolLabel, gridBagConstraints);

        c1CurveFitTolTextField.setText(String.valueOf(UserVariables.getC1CurveFitTol()));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(c1CurveFitTolTextField, gridBagConstraints);

        gpuToggleButton.setText(gpuToggleText);
        gpuToggleButton.setEnabled(analyser.isGpuEnabled());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanel1.add(gpuToggleButton, gridBagConstraints);

        minTrajDistLabel.setText(minTrajDistLabelText);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanel1.add(minTrajDistLabel, gridBagConstraints);

        minTrajDistTextField.setText(String.valueOf(UserVariables.getMinTrajDist()));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(minTrajDistTextField, gridBagConstraints);

        trackLengthLabel.setText(trackLengthText);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanel1.add(trackLengthLabel, gridBagConstraints);

        trackLengthTextField.setText(String.valueOf(UserVariables.getTrackLength()));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(trackLengthTextField, gridBagConstraints);

        previewResultsToggleButton.setText(prevResToggleText);
        previewResultsToggleButton.setSelected(UserVariables.isPrevRes());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanel1.add(previewResultsToggleButton, gridBagConstraints);

        useCalsToggleButton.setText(useCalToggleText);
        useCalsToggleButton.setSelected(UserVariables.isUseCals());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanel1.add(useCalsToggleButton, gridBagConstraints);

        extractSigsToggleButton.setText(extractSigsToggleText);
        extractSigsToggleButton.setSelected(UserVariables.isExtractsigs());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        jPanel1.add(extractSigsToggleButton, gridBagConstraints);

        minTrajMSDLabel.setText(minTrajMSDLabelText);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanel1.add(minTrajMSDLabel, gridBagConstraints);

        minTrajMSDTextField.setText(String.valueOf(UserVariables.getMsdThresh()));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(minTrajMSDTextField, gridBagConstraints);

        colocalToggleButton.setText(colocalToggleText);
        colocalToggleButton.setSelected(UserVariables.isColocal());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanel1.add(colocalToggleButton, gridBagConstraints);

        colocalThreshLabel.setText(colocalThreshText);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanel1.add(colocalThreshLabel, gridBagConstraints);

        colocalThreshTextField.setText(String.valueOf(UserVariables.getColocalThresh()));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(colocalThreshTextField, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.8;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new java.awt.GridBagLayout());

        canvas1.setPreferredSize(new java.awt.Dimension(
            imp.getProcessor().getWidth(), imp.getProcessor().getHeight()));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridwidth = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 0.8;
    gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
    jPanel2.add(canvas1, gridBagConstraints);

    previewScrollBar.setOrientation(javax.swing.JScrollBar.HORIZONTAL);
    previewScrollBar.setModel(new DefaultBoundedRangeModel(1, 0, 1, analyser.getStacks()[0].getSize()));
    previewScrollBar.setEnabled(previewToggleButton.isSelected());
    previewScrollBar.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
        public void adjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {
            previewScrollBarAdjustmentValueChanged(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.weightx = 0.8;
    gridBagConstraints.weighty = 0.1;
    gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 0);
    jPanel2.add(previewScrollBar, gridBagConstraints);

    previewTextField.setText(String.valueOf(previewScrollBar.getValue()));
    previewTextField.setEditable(false);
    previewTextField.setEnabled(previewToggleButton.isSelected());
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    gridBagConstraints.weightx = 0.2;
    gridBagConstraints.weighty = 0.1;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 10);
    jPanel2.add(previewTextField, gridBagConstraints);

    previewToggleButton.setText("Preview");
    previewToggleButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            previewToggleButtonActionPerformed(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.gridwidth = 2;
    gridBagConstraints.weighty = 0.1;
    gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
    jPanel2.add(previewToggleButton, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.weightx = 0.8;
    gridBagConstraints.weighty = 0.8;
    getContentPane().add(jPanel2, gridBagConstraints);

    jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    jPanel3.setLayout(new java.awt.GridBagLayout());

    okButton.setText("Run");
    okButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            okButtonActionPerformed(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    jPanel3.add(okButton, gridBagConstraints);

    cancelButton.setText("Cancel");
    cancelButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cancelButtonActionPerformed(evt);
        }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    jPanel3.add(cancelButton, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.gridwidth = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weighty = 0.2;
    getContentPane().add(jPanel3, gridBagConstraints);

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.dispose();
        wasOKed = false;
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        if (!setVariables()) {
            return;
        }
        this.dispose();
        wasOKed = true;
    }//GEN-LAST:event_okButtonActionPerformed

    private void previewScrollBarAdjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {//GEN-FIRST:event_previewScrollBarAdjustmentValueChanged
        previewTextField.setText(String.valueOf(previewScrollBar.getValue()));
        if (previewScrollBar.getValueIsAdjusting() || !setVariables()) {
            return;
        }
        viewDetections();
    }//GEN-LAST:event_previewScrollBarAdjustmentValueChanged

    private void previewToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previewToggleButtonActionPerformed
        previewScrollBar.setEnabled(previewToggleButton.isSelected());
        previewTextField.setEnabled(previewToggleButton.isSelected());
        previewScrollBarAdjustmentValueChanged(null);
    }//GEN-LAST:event_previewToggleButtonActionPerformed

    boolean setVariables() {
        try {
            UserVariables.setChan1MaxThresh(Double.parseDouble(chan1MaxThreshTextField.getText()));
//            UserVariables.setChan2MaxThresh(Double.parseDouble(chan2MaxThreshTextField.getText()));
            UserVariables.setMinTrajLength(Double.parseDouble(minTrajLengthTextField.getText()));
            UserVariables.setMinTrajDist(Double.parseDouble(minTrajDistTextField.getText()));
            UserVariables.setMsdThresh(Double.parseDouble(minTrajMSDTextField.getText()));
            UserVariables.setSpatialRes(Double.parseDouble(spatResTextField.getText()));
            UserVariables.setTimeRes(Double.parseDouble(timeResTextField.getText()));
            UserVariables.setTrajMaxStep(Double.parseDouble(maxTrajStepTextField.getText()));
            UserVariables.setCurveFitTol(Double.parseDouble(c1CurveFitTolTextField.getText()));
//            UserVariables.setC2CurveFitTol(Double.parseDouble(c2CurveFitTolTextField.getText()));
            UserVariables.setColocal(colocalToggleButton.isSelected());
            UserVariables.setPreProcess(preprocessToggleButton.isSelected());
            UserVariables.setC1Index(c1ComboBox.getSelectedIndex());
//            UserVariables.setC2Index(c2ComboBox.getSelectedIndex());
            UserVariables.setGpu(gpuToggleButton.isSelected());
            UserVariables.setTrackLength(Double.parseDouble(trackLengthTextField.getText()));
            UserVariables.setPrevRes(previewResultsToggleButton.isSelected());
            UserVariables.setUseCals(useCalsToggleButton.isSelected());
            UserVariables.setExtractsigs(extractSigsToggleButton.isSelected());
            UserVariables.setColocalThresh(Double.parseDouble(colocalThreshTextField.getText()));
//            printParams();
        } catch (NumberFormatException e) {
            IJ.error("Number formatting error " + e.toString());
            return false;
        }
        return true;
    }

    public void viewDetections() {
        analyser.calcParticleRadius(UserVariables.getSpatialRes());
        ImageStack stacks[] = analyser.getStacks();
        ParticleArray detections;
        if (analyser instanceof GPU_Analyse && UserVariables.isGpu()) {
            detections = ((GPU_Analyse) analyser).cudaFindParticles1C(true, previewScrollBar.getValue() - 1, previewScrollBar.getValue() - 1, stacks[1]);
        } else {
            detections = analyser.findParticles(true, previewScrollBar.getValue() - 1, previewScrollBar.getValue() - 1, UserVariables.getC1CurveFitTol(), stacks[0], stacks[1], true, UserVariables.isColocal(), true, UserVariables.getC2CurveFitTol());
        }
        if (detections != null) {
            ImageProcessor output = Utils.updateImage(stacks[0], stacks[1], previewScrollBar.getValue());
            double mag = 1.0 / UIMethods.getMagnification(output, canvas1);
            double sr = 1.0 / Double.parseDouble(spatResTextField.getText());
//        int radius = (int)Math.round(sr);
            int radius = analyser.calcParticleRadius(UserVariables.getSpatialRes());
            IsoGaussian c1, c2;
            ArrayList<Particle> particles = detections.getLevel(0);
            Color c1Color = !monoChrome ? analyser.getDrawColor(c1ComboBox.getSelectedIndex()) : Color.white;
            Color c2Color = !monoChrome ? analyser.getDrawColor(1 - c1ComboBox.getSelectedIndex()) : Color.white;
            output.setLineWidth(1);
            for (Particle particle : particles) {
                c1 = particle.getC1Gaussian();
                c2 = particle.getC2Gaussian();
                drawDetections(output, (int) (Math.round(sr * c1.getX())), (int) (Math.round(sr * c1.getY())),
                        radius, true, c1Color);
                if (c2 != null) {
                    drawDetections(output, (int) (Math.round(sr * c2.getX())),
                            (int) (Math.round(sr * c2.getY())), radius,
                            false, c2Color);
                }
            }
            imp.setProcessor("", output);
            ((ImageCanvas) canvas1).setMagnification(mag);

            canvas1.repaint();
        }
    }

    public void drawDetections(ImageProcessor image, int x, int y, int radius,
            boolean drawOval, Color colour) {
        image.setColor(colour);
        if (drawOval) {
            image.drawOval((x - radius), (y - radius), 2 * radius, 2 * radius);
        } else {
            image.drawRect((x - radius), (y - radius), 2 * radius, 2 * radius);
        }
    }

    public boolean isWasOKed() {
        return wasOKed;
    }

    public static String getChannel1LabelText() {
        return channel1LabelText;
    }

    public static String getChannel2LabelText() {
        return channel2LabelText;
    }

    public static String getSpatResLabelText() {
        return spatResLabelText;
    }

    public static String getFpsLabelText() {
        return fpsLabelText;
    }

    public static String getMinTrajLengthLabelText() {
        return minTrajLengthLabelText;
    }

    public static String getMaxLinkDistLabelText() {
        return maxLinkDistLabelText;
    }

    public static String getChan1MaxThreshLabelText() {
        return chan1MaxThreshLabelText;
    }

//    public static String getChan2MaxThreshLabelText() {
//        return chan2MaxThreshLabelText;
//    }

    public static String getCurveFitTolLabelText() {
        return c1CurveFitTolLabelText;
    }

//    public static String getC2CurveFitTolLabelText() {
//        return c2CurveFitTolLabelText;
//    }

    public static String getColocalToggleText() {
        return colocalToggleText;
    }

    public static String getPreprocessToggleText() {
        return preprocessToggleText;
    }

    public static String getGpuToggleText() {
        return gpuToggleText;
    }

    public static String getTrackLengthText() {
        return trackLengthText;
    }

    public static String getMinTrajDistLabelText() {
        return minTrajDistLabelText;
    }

    public static String getPrevResToggleText() {
        return prevResToggleText;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox c1ComboBox;
    private javax.swing.JLabel c1CurveFitTolLabel;
    private javax.swing.JTextField c1CurveFitTolTextField;
    private javax.swing.JLabel c1Label;
    private javax.swing.JButton cancelButton;
    private java.awt.Canvas canvas1;
    private javax.swing.JLabel chan1MaxThreshLabel;
    private javax.swing.JTextField chan1MaxThreshTextField;
    private javax.swing.JLabel colocalThreshLabel;
    private javax.swing.JTextField colocalThreshTextField;
    private javax.swing.JToggleButton colocalToggleButton;
    private javax.swing.JToggleButton extractSigsToggleButton;
    private javax.swing.JToggleButton gpuToggleButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel maxTrajStepLabel;
    private javax.swing.JTextField maxTrajStepTextField;
    private javax.swing.JLabel minTrajDistLabel;
    private javax.swing.JTextField minTrajDistTextField;
    private javax.swing.JLabel minTrajLengthLabel;
    private javax.swing.JTextField minTrajLengthTextField;
    private javax.swing.JLabel minTrajMSDLabel;
    private javax.swing.JTextField minTrajMSDTextField;
    private javax.swing.JButton okButton;
    private javax.swing.JToggleButton preprocessToggleButton;
    private javax.swing.JToggleButton previewResultsToggleButton;
    private javax.swing.JScrollBar previewScrollBar;
    private javax.swing.JTextField previewTextField;
    private javax.swing.JToggleButton previewToggleButton;
    private javax.swing.JLabel spatResLabel;
    private javax.swing.JTextField spatResTextField;
    private javax.swing.JLabel timeResLabel;
    private javax.swing.JTextField timeResTextField;
    private javax.swing.JLabel trackLengthLabel;
    private javax.swing.JTextField trackLengthTextField;
    private javax.swing.JToggleButton useCalsToggleButton;
    // End of variables declaration//GEN-END:variables
}
