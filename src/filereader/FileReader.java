package filereader;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import datastructures.DefaultBinaryTree;
import datastructures.DefaultBinaryTreeNode;

/**
 * This class reads and parses an XML file
 * 
 * @author Kuan Chi Chen
 *
 */
public class FileReader {
	/**
	 * the DefaultBinaryTree of type String that the XML file is parsed into
	 **/
	private DefaultBinaryTree<String> myTree;

	/**
	 * This is creating a Document object, which takes the XML file and parses
	 * it into something that can be read by the program.
	 * 
	 */
	public FileReader() {
		// Setup XML Document
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// get the XML file that I wrote
		File xmlFile = new File("questions.xml");
		try {
			// parse the XML file into a Document
			Document document = builder.parse(xmlFile);
			// use the Documemt to create my Tree
			myTree = parseQuestionFile(document);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method is to navigate through the major document structure.
	 * 
	 * @param document
	 */
	private static DefaultBinaryTree<String> parseQuestionFile(Document document) {
		// get an Document Element from the document passed in
		Node docRoot = document.getDocumentElement();
		// create a DefaultBinaryTree of type String
		DefaultBinaryTree<String> Questions = new DefaultBinaryTree<String>();
		// calls parseNode, passing in the root node of the Document
		// set the returned DefaultBinaryTreeNode of type String to be the root
		// of Questions
		Questions.setRoot(parseNode(docRoot));
		// return our DefaultBinaryTree of type String
		return Questions;
	}

	/**
	 * This method is recursive, to be called on all nodes in the document, as
	 * well as all nodes that a particular node has.
	 * 
	 * @param n
	 */
	private static DefaultBinaryTreeNode<String> parseNode(Node n) {
		// the String representation of the root of the XML file
		String root = "";

		// if the Node n has the nodeType Element, we can cast it
		if (n.getNodeType() == Node.ELEMENT_NODE) {
			// get the current Element
			Element currentElt = (Element) n;
			// get the root
			root = currentElt.getAttribute("text");
		}
		// instantiate the left and right nodes of our Node n
		DefaultBinaryTreeNode<String> left = null;
		DefaultBinaryTreeNode<String> right = null;

		// if the Node n has any children
		// Collect the child Nodes in a variable of type "NodeList"
		NodeList nodeList = n.getChildNodes();

		// go through the whole nodeList
		for (int i = 0; i < nodeList.getLength(); i++) {
			// for each item in the nodeList
			Node childNode = nodeList.item(i);
			// if this item has the nodeType Element, we can cast it
			if (childNode.getNodeType() == Node.ELEMENT_NODE) {
				// get the current Element that would be the answerNodes
				Element answerNode = (Element) childNode;
				// Collect the child Nodes of the answerNodes in another
				// "NodeList"
				NodeList nodes = answerNode.getChildNodes();
				// go through the NodeList nodes
				for (int j = 0; j < nodes.getLength(); j++) {
					// for each item in nodes
					Node node = nodes.item(j);
					// if its node name is thing
					if (node.getNodeName().equals("thing")) {
						// and if its answerNode with Attribute useranswer is
						// yes
						if (answerNode.getAttribute("useranswer").equals("yes")) {
							// set the left node with the text content of this
							// node
							left = new DefaultBinaryTreeNode<String>(node.getTextContent());
						}
						// else if its answerNode with Attribute useranswer is
						// no
						else if (answerNode.getAttribute("useranswer").equals("no")) {
							// set the right node with the text content of this
							// node
							right = new DefaultBinaryTreeNode<String>(node.getTextContent());
						}
					}
					// else if its node name is question
					else if (node.getNodeName().equals("question")) {
						// and if its answerNode with Attribute useranswer is
						// yes
						if (answerNode.getAttribute("useranswer").equals("yes")) {
							// set the left node to be the node returned by
							// calling parseNode and passing in this node
							left = parseNode(node);
						}
						// else if its answerNode with Attribute useranswer is
						// no
						else if (answerNode.getAttribute("useranswer").equals("no")) {
							// set the right node to be the node returned by
							// calling parseNode and passing in this node
							right = parseNode(node);
						}
					}
				}
			}
		}
		// return the DefaultBinaryTreeNode with the root, left and right nodes
		// created
		return new DefaultBinaryTreeNode<String>(root, left, right);
	}

	/**
	 * Pass the DefaultBinaryTree created to other classes
	 * 
	 * @return myTree DefaultBinaryTreeNode of type String
	 */
	public DefaultBinaryTree<String> getTree() {
		return myTree;
	}

}
