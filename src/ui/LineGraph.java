package ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LineGraph extends JPanel {

    private final Color compoundColor = new Color(44, 102, 230, 180);
    private final Color simpleColor = new Color(241, 24, 76, 180);
    private final Color pointColor = new Color(100, 100, 100, 180);
    private final Color gridColor = new Color(200, 200, 200, 200);
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
    private final List<Double> compound;
    private final List<Double> simple;

    public LineGraph(List<Double> compound, List<Double> simple) {
        this.compound = compound;
        this.simple = simple;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int padding = 25;
        int labelPadding = 25;
        double xScale = ((double) getWidth() - 2 * padding - labelPadding) / (compound.size() - 1);
        double yScale = ((double) getHeight() - 2 * padding - labelPadding) / (getMaxScore() - getMinScore());

        List<Point> compoundPoints = new ArrayList<>();
        List<Point> simplePoints = new ArrayList<>();
        for (int i = 0; i < compound.size(); i++) {
            int x1 = (int) (i * xScale + padding + labelPadding);
            int y1 = (int) ((getMaxScore() - compound.get(i)) * yScale + padding);
            compoundPoints.add(new Point(x1, y1));

            int x2 = (int) (i * xScale + padding + labelPadding);
            int y2 = (int) ((getMaxScore() - simple.get(i)) * yScale + padding);
            simplePoints.add(new Point(x2, y2));
        }

        // draw white background
        g2.setColor(Color.WHITE);
        g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding,
                getHeight() - 2 * padding - labelPadding);
        g2.setColor(Color.BLACK);

        // create hatch marks and grid lines for y axis.
        int pointWidth = 4;
        int numberYDivisions = 10;
        for (int i = 0; i < numberYDivisions + 1; i++) {
            int x0 = padding + labelPadding;
            int x1 = pointWidth + padding + labelPadding;
            int y0 = getHeight()
                    - ((i * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding);
            if (compound.size() > 0) {
                g2.setColor(gridColor);
                g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y0);
                g2.setColor(Color.BLACK);
                String yLabel = ((int) ((getMinScore()
                        + (getMaxScore() - getMinScore()) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0 + "";
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
            }
            g2.drawLine(x0, y0, x1, y0);
        }

        // and for x axis
        for (int i = 0; i < compound.size(); i++) {
            if (compound.size() > 1) {
                int x0 = i * (getWidth() - padding * 2 - labelPadding) / (compound.size() - 1) + padding + labelPadding;
                int y0 = getHeight() - padding - labelPadding;
                int y1 = y0 - pointWidth;
                if ((i % ((int) ((compound.size() / 20.0)) + 1)) == 0) {
                    g2.setColor(gridColor);
                    g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x0, padding);
                    g2.setColor(Color.BLACK);
                    String xLabel = i + "";
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                }
                g2.drawLine(x0, y0, x0, y1);
            }
        }

        Stroke oldStroke = g2.getStroke();
        g2.setColor(compoundColor);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < compoundPoints.size() - 1; i++) {
            int x1 = compoundPoints.get(i).x;
            int y1 = compoundPoints.get(i).y;
            int x2 = compoundPoints.get(i + 1).x;
            int y2 = compoundPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }
        g2.setColor(simpleColor);
        for (int i = 0; i < simplePoints.size() - 1; i++) {
            int x1 = simplePoints.get(i).x;
            int y1 = simplePoints.get(i).y;
            int x2 = simplePoints.get(i + 1).x;
            int y2 = simplePoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }

        g2.setStroke(oldStroke);
        g2.setColor(pointColor);
        for (int i = 0; i < compoundPoints.size(); i++) {
            int x = compoundPoints.get(i).x - pointWidth / 2;
            int y = compoundPoints.get(i).y - pointWidth / 2;
            g2.fillOval(x, y, pointWidth, pointWidth);

            int x2 = simplePoints.get(i).x - pointWidth / 2;
            int y2 = simplePoints.get(i).y - pointWidth / 2;
            g2.fillOval(x2, y2, pointWidth, pointWidth);
        }
    }

    private double getMinScore() {
        double minScore = Double.MAX_VALUE;
        for (Double score : compound) {
            minScore = Math.min(minScore, score);
        }
        return minScore;
    }

    private double getMaxScore() {
        double maxScore = Double.MIN_VALUE;
        for (Double score : compound) {
            maxScore = Math.max(maxScore, score);
        }
        return maxScore;
    }
}