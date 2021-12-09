package fdrc.client;


//import com.sun.xml.txw2.output.XmlSerializer;

import java.io.IOException;
import java.io.StringWriter;

//public class Test {
//    public static void Pullxml(String[] args) {
//        Example ex = new Example();
//        try {
//            StringWriter stringWriter = new StringWriter();
//            XmlSerializer serializer = XmlPullParserFactory.newInstance().newSerializer();
//            serializer.setOutput(stringWriter);
//            serializer.startDocument(null, true);
//            serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
//            serializer.endDocument();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (XmlPullParserException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        XStream xStream = new XStream();
//        Example ex = new Example("Test", 1);
//        System.out.println(xStream.toXML(ex));
//    }
//}

class Example {

    private String text;

    private int index;

    public Example() {
        super();
    }

    public Example(String text, int index) {
        this.text = text;
        this.index = index;
    }

    public String getMessage() {
        return text;
    }

    public int getId() {
        return index;
    }
}

