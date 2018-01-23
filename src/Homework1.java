import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.tree.DefaultTreeCellRenderer;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import javax.swing.ImageIcon;



import java.awt.Dimension;
import java.awt.GridLayout;



public class Homework1 extends JPanel
		               implements TreeSelectionListener {
	static Node n;
	JTree tree;
	JEditorPane htmlPane;

	public Homework1() {
		super(new GridLayout(1,0));

		//Create the nodes.

		DefaultMutableTreeNode top=createNodes(n);

		//Create a tree that allows one selection at a time.
		tree = new JTree(top);
		tree.getSelectionModel().setSelectionMode
				(TreeSelectionModel.SINGLE_TREE_SELECTION); //เลือกได้อันเดียว

		//Listen for when the selection changes.
		tree.addTreeSelectionListener(this);//จำว่าเราคลิกตัวไหนไป


		tree.putClientProperty("JTree.lineStyle","None");
		ImageIcon tutorialIcon =  createImageIcon("middle.gif");

		DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
		renderer.setOpenIcon(tutorialIcon);
		renderer.setClosedIcon(tutorialIcon);
		tree.setCellRenderer(renderer);


		//Create the scroll pane and add the tree to it.
		JScrollPane treeView = new JScrollPane(tree);//หน้าต่างของมัน

		//Create the HTML viewing pane.
		htmlPane = new JEditorPane();
		htmlPane.setEditable(false);//เเก้ค่าไม่ได้

		JScrollPane htmlView = new JScrollPane(htmlPane);

		//Add the scroll panes to a split pane.
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane.setTopComponent(treeView);
		splitPane.setBottomComponent(htmlView);

		Dimension minimumSize = new Dimension(100, 50);
		htmlView.setMinimumSize(minimumSize);//เเถบกลาง
		treeView.setMinimumSize(minimumSize);
		splitPane.setDividerLocation(100);
		splitPane.setPreferredSize(new Dimension(500, 300));

		//Add the split pane to this panel.
		add(splitPane);

		}


		public DefaultMutableTreeNode createNodes(Node n){
			DefaultMutableTreeNode n1=new DefaultMutableTreeNode(n);

			if(n.getLeft()!=null) {
				DefaultMutableTreeNode nL=createNodes(n.getLeft());
				n1.add(nL);
			}
			if(n.getRight()!=null){
				DefaultMutableTreeNode nR=createNodes(n.getRight());
				n1.add(nR);

			}
			return n1;
		}

	public static void main(String[] args) {
		String input = "251-*32*+";
		// Begin of arguments input sample
		if (args.length > 0) {

			// TODO: Implement your project here

		}
		 n = new Node();
		Node temp = n;
		for (int i = 0; i < input.length(); i++) {
			temp.setRight(new Node());
			temp = temp.getRight();
			temp.setValue(String.valueOf(input.charAt(i)));
		}
		n = infix(n.getRight());
		String infix = inorder(n);
		System.out.println(infix.substring(1, infix.length() - 1) + "=" + calculater(n));
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
	private static void createAndShowGUI() {

		//Create and set up the window.
		JFrame frame = new JFrame("BinarytreeCalculater");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Add content to the window.
		frame.add(new Homework1());

		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static Node infix(Node n) {
		Node tmp = n;
		while (tmp != null){
			n = tmp;
			tmp = n.getRight();
			if (n.getValue().matches("[0-9]")) {
				n.setRight(null);
			} else {
				if (n.getLeft().getValue().matches("[0-9]")) {
					n.setRight(n.getLeft());
					n.setLeft(n.getRight().getLeft());
					n.getRight().setLeft(null);
				} else {
					n.setRight(n.getLeft());
					n.setLeft(n.getRight().getLeft().getLeft());
					n.getRight().getLeft().setLeft(null);
				}
			}
			if (tmp != null) tmp.setLeft(n);
		}
		return n;
	}

	public static String inorder(Node n) {
		String left = "";
		String right = "";
		if (n.getLeft() != null) {
			left = "(" + inorder(n.getLeft());
		}
		if (n.getRight() != null) {
			right = inorder(n.getRight()) + ")";
		}

		return (left + n.getValue() + right);
	}

	public static int calculater(Node n) {

		if (n.getValue().matches("[0-9]")) return Integer.valueOf(n.getValue());

		int result = 0;
		int left = calculater(n.getLeft());
		int right = calculater(n.getRight());

		switch (n.getValue()){
			case "+":result = left + right;break;
			case "-":result = left - right;break;
			case "*":result = left * right;break;
			case "/":result = left / right;break;
		}

		return result;
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)
				tree.getLastSelectedPathComponent();
		Object nodeInfo = node.getUserObject();
		String text = inorder((Node)nodeInfo);
		if(text.length()!=1) {
			text=text.substring(1,text.length()-1)+"="+calculater((Node)nodeInfo);

		}


		htmlPane.setText(text);


	}
	protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Homework1.class.getResource(path);
        if (imgURL != null) {
         return new ImageIcon(imgURL);
		} else {
		System.err.println("Couldn't find file: " + path);
		return null;
		}
}
}
