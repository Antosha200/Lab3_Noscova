import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DrawingComp extends JPanel {
    private LinkedList<Point> points = new LinkedList<Point>();

    public DrawingComp() {
        setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                points.add(new Point(e.getX(), e.getY()));
                repaint();
            }

        });

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gr = (Graphics2D) g;
        gr.setStroke(new BasicStroke(2));
        gr.setColor(Color.BLACK);
        gr.drawLine(0, this.getHeight() / 2, this.getWidth(), this.getHeight() / 2);
        gr.drawLine(this.getWidth() / 2, 0, this.getWidth() / 2, this.getHeight());
        gr.setStroke(new BasicStroke(1));
        int buf1 = this.getHeight() / 2 + 10;
        int buf2 = this.getHeight() / 2 - 10;
        while (buf1 < this.getHeight()) {
            gr.drawLine(this.getWidth() / 2 - 3, buf1, this.getWidth() / 2 + 3, buf1);
            buf1 += 10;
            gr.drawLine(this.getWidth() / 2 - 3, buf2, this.getWidth() / 2 + 3, buf2);
            buf2 -= 10;
        }
        buf1 = this.getWidth() / 2 + 10;
        buf2 = this.getWidth() / 2 - 10;
        while (buf1 < this.getWidth()) {
            gr.drawLine(buf1, this.getHeight() / 2 - 3, buf1, this.getHeight() / 2 + 3);
            buf1 += 10;
            gr.drawLine(buf2, this.getHeight() / 2 - 3, buf2, this.getHeight() / 2 + 3);
            buf2 -= 10;
        }
        Iterator<Point> it = points.iterator();
        gr.setColor(Color.BLACK);
        while (it.hasNext()) {
            Point p = it.next();
            if (it.hasNext()) {
                Point p1 = it.next();
                algorithm(gr, (int) p.getX(), (int) p.getY(), (int) p1.getX(), (int) p1.getY());
            }
        }
    }

    private void algorithm(Graphics2D gr, int x1, int y1, int x2, int y2) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        int d = dy + dy - dx;
        int[][] p;
        boolean yless0 = false;
        boolean xless0 = false;
        boolean dxlessdy = false;
        if (dy < 0) {
            yless0 = true;
            dy = -dy;
        }
        if (dx < 0) {
            xless0 = true;
            dx = -dx;
        }
        if (Math.abs(dx) < Math.abs(dy)) {
            dxlessdy = true;
            d = dx + dx - dy;
            p = new int[Math.abs(dy)][2];
        } else p = new int[Math.abs(dx)][2];

        p[0][0] = x1;
        p[0][1] = y1;
        if (d >= 0) {
            if (dxlessdy) {
                d = d + 2 * (dx - dy);
            } else {
                d = d + 2 * (dy - dx);
            }
        } else {
            if (dxlessdy) {
                d = d + 2 * dx;
            } else {
                d = d + 2 * dy;
            }
        }
        gr.drawLine(p[0][0], p[0][1], p[0][0], p[0][1]);
        for (int i = 1; i < ((dy >= dx) ? dy : dx ) - 1; i++) {
            if (d >= 0) {
                if (dxlessdy) {
                    if (xless0) p[i][0] = p[i - 1][0] - 1;
                    else p[i][0] = p[i - 1][0] + 1;
                    if (yless0) p[i][1] = p[i - 1][1] - 1;
                    else p[i][1] = p[i - 1][1] + 1;
                    d = d + 2 * (dx - dy);
                } else {
                    if (xless0) p[i][0] = p[i - 1][0] - 1;
                    else p[i][0] = p[i - 1][0] + 1;
                    if (yless0) p[i][1] = p[i - 1][1] - 1;
                    else p[i][1] = p[i - 1][1] + 1;
                    d = d + 2 * (dy - dx);
                }
            } else {
                if (dxlessdy) {
                    p[i][0] = p[i - 1][0];
                    if (yless0) p[i][1] = p[i - 1][1] - 1;
                    else p[i][1] = p[i - 1][1] + 1;
                    d = d + 2 * dx;
                } else {
                    if (xless0) p[i][0] = p[i - 1][0] - 1;
                    else p[i][0] = p[i - 1][0] + 1;
                    p[i][1] = p[i - 1][1];
                    d = d + 2 * dy;
                }
            }
            gr.drawLine(p[i][0], p[i][1], p[i][0], p[i][1]);
        }
    }
}