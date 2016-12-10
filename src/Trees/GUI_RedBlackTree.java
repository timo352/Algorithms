package Trees;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import javax.swing.*;
import java.util.*;

public final class GUI_RedBlackTree<T extends Comparable<? super T>> extends JFrame {

	RedBlackTree<T> tree = new RedBlackTree();

	private static double radius = 20.0;

	private int width = 0;
	private int height = 0;
	private int padding = 0;

	private BufferedImage graphicsContext;
	JPanel contentPanel = new JPanel();
	JLabel contextRender;
	Stroke solidStroke = new BasicStroke(1.4f);
	RenderingHints antialiasing;
	Random random = new Random();

	GUI_RedBlackTree() {
		GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] devices = g.getScreenDevices();

		width = devices[0].getDisplayMode().getWidth();
		height = devices[0].getDisplayMode().getHeight();
		
		antialiasing = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphicsContext = new BufferedImage(width + (2 * padding), height + (2 * padding), BufferedImage.TYPE_INT_RGB);
		contextRender = new JLabel(new ImageIcon(graphicsContext));

		contentPanel.add(contextRender);

		contentPanel.setSize(width, height);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.addComponentListener(new ComponentListener() {
			public void componentResized(ComponentEvent e) {

				int x = contentPanel.getWidth();

				graphicsContext = new BufferedImage(contentPanel.getWidth(), contentPanel.getHeight(), BufferedImage.TYPE_INT_ARGB);
				rePaint();
			}

			public void componentMoved(ComponentEvent e) {
			}

			public void componentShown(ComponentEvent e) {
			}

			public void componentHidden(ComponentEvent e) {
			}
		});

		this.setResizable(true);
		this.setContentPane(contentPanel);
		//take advantage of auto-sizing the window based on the size of its contents
		this.pack();
		this.setLocationRelativeTo(null);
		this.paint();
		setVisible(true);
	}

	private void rePaint() {
		this.printTree();
	}

	public static void main(String[] args) {

		Runnable swingStarter = new Runnable() {
			public void run() {
				GUI_RedBlackTree<Integer> guiTree = new GUI_RedBlackTree();

				Random r = new Random(System.currentTimeMillis());
				for (int i = 0; i < 20; i++) {
					guiTree.tree.insert(r.nextInt(500));
				}

				guiTree.printTree();
			}
		};

		SwingUtilities.invokeLater(swingStarter);
	}

	public void paint() {

		Graphics2D g2d = graphicsContext.createGraphics();
		g2d.setRenderingHints(antialiasing);

		//clear the background
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, graphicsContext.getWidth(), graphicsContext.getHeight());

		//force the container for the context to re-paint itself
		contextRender.repaint();
	}

	public void printTree() {
		double w = contextRender.getSize().getWidth();
		printNode(tree.getRoot(), new Point2D.Double(w / 2, 2 * radius), 0, tree.height());
	}

	private void printNode(RBNode n, Point2D p, int depth, int height) {

		Graphics2D g2d = graphicsContext.createGraphics();
		g2d.setRenderingHints(antialiasing);
		g2d.setClip(null);
		g2d.setStroke(solidStroke);
		g2d.setColor(Color.BLACK);

		Ellipse2D nodeCircle = getCircle(p, radius);
		double change = Math.pow(2, height - depth + 1) * radius / 3.35;
		if (n.left != tree.getNIL()) {
			Point2D p1 = new Point2D.Double(p.getX() - change, p.getY() + 2 * radius);
			Line2D leftLine = getVector(p, p1);
			g2d.draw(leftLine);
			printNode(n.left, p1, depth + 1, height);
		} else {
			Point2D p1 = new Point2D.Double(p.getX() - 1.10 * radius, p.getY() + 1.10 * radius);
			Line2D leftLine = getVector(p, p1);
			g2d.draw(leftLine);
		}
		if (n.right != tree.getNIL()) {
			Point2D p2 = new Point2D.Double(p.getX() + change, p.getY() + 2 * radius);
			Line2D rightLine = getVector(p, p2);
			g2d.draw(rightLine);
			printNode(n.right, p2, depth + 1, height);
		} else {
			Point2D p2 = new Point2D.Double(p.getX() + 1.10 * radius, p.getY() + 1.10 * radius);
			Line2D rightLine = getVector(p, p2);
			g2d.draw(rightLine);
		}

		g2d.setColor(Color.WHITE);
		g2d.fill(nodeCircle);

		if (n.color == RBNode.RED) {
			g2d.setColor(Color.RED);
		} else {
			g2d.setColor(Color.BLACK);
		}
		g2d.draw(nodeCircle);

		g2d.setFont(new Font("Calibri", Font.BOLD, 10));
		FontMetrics f = g2d.getFontMetrics();
		float w = f.stringWidth(n.toString()) / (float) 2.0;
		float h = f.getHeight();
		g2d.drawString(n.toString(), (float) p.getX() - w, (float) p.getY() + h / 3);

		//g2d.drawString(n.key.toString(), (float)p.getX()-3, (float)p.getY()+2);
		g2d.dispose();
	}

	private static Line2D getVector(Point2D start, Point2D end) {
		Line2D vector = new Line2D.Double(start, end);
		return vector;
	}

	private static Ellipse2D getCircle(Point2D center, double radius) {
		Ellipse2D.Double myCircle = new Ellipse2D.Double(center.getX() - radius, center.getY() - radius, 2 * radius, 2 * radius);
		return myCircle;
	}
}
