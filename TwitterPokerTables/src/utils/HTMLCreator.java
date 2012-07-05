package utils;

public class HTMLCreator {

	public static String createHTML(StringBuilder pSb) {

		StringBuilder sb = new StringBuilder();
		
		sb.append("<html>\n");
		sb.append("\t<head>\n");
		sb.append("\t</head>\n");
		sb.append("\t<body>\n");
		sb.append(pSb.toString() + "\n");
		sb.append(TweetsEmbedder.SCRIPT + "\n");
		sb.append("\t</body>\n");
		sb.append("</html>\n");
		
		FileManipulator.writeToFile(sb.toString(), "tweets.html");
		
		return sb.toString();
	}

}
