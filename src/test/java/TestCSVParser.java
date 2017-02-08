import CSVHandle.CSVParser;
import CSVHandle.IllegalCSVFormatException;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by Simba on 2017/2/7.
 */
public class TestCSVParser {
    @Test
    public void testCsvParser() throws IllegalCSVFormatException {
        CSVParser csvParser = new CSVParser();

        String csv1 = "123,456,789";
        List<List<String>> res1 = csvParser.parse(csv1);
        Assert.assertEquals("has more than one line", 1, res1.size());
        Assert.assertArrayEquals("contents are not same", res1.get(0).toArray(), new String[]{"123", "456", "789"});

        String csv2 = "123,456,789\n234,567,890";
        csvParser.clean();
        List<List<String>> res2 = csvParser.parse(csv2);
        Assert.assertEquals("doesn't have two lines", 2, res2.size());
        Assert.assertArrayEquals("contents are not same", res2.get(1).toArray(), new String[]{"234", "567", "890"});

        String csv3 = "123,  4  56,789";
        csvParser.clean();
        List<List<String>> res3 = csvParser.parse(csv3);
        Assert.assertEquals("wrongly parses strings which has blanks", "  4  56", res3.get(0).get(1));

        String csv4 = "123,\"456\n321\",789";
        csvParser.clean();
        List<List<String>> res4 = csvParser.parse(csv4);
        Assert.assertEquals("has more than one line", 1, res4.size());
        Assert.assertArrayEquals("contents are not same", res4.get(0).toArray(), new String[]{"123", "456\n321", "789"});

        String csv5 = "123,\"\"456,789";
        csvParser.clean();
        List<List<String>> res5 = csvParser.parse(csv5);
        Assert.assertEquals("wrongly parses quote", "\"456", res5.get(0).get(1));

        String csv6 = "123,\"45\"\"321,,36\",789";
        csvParser.clean();
        List<List<String>> res6 = csvParser.parse(csv6);
        Assert.assertEquals("has more than three columns", 3, res6.get(0).size());
        Assert.assertEquals("wrongly parses quote or comma", "45\"321,,36", res6.get(0).get(1));

        String csv7 = "123,  \"1234,789";
        csvParser.clean();
        try {
            List<List<String>> res7 = csvParser.parse(csv7);
            Assert.assertTrue("should not reach here", false);
        } catch (IllegalCSVFormatException e) {
            // Do nothing
        }

        String csv8 = "123,\"1234";
        csvParser.clean();
        try {
            List<List<String>> res8 = csvParser.parse(csv8);
            Assert.assertTrue("should not reach here", false);
        } catch (IllegalCSVFormatException e) {
            // Do nothing
        }

        String csv9 = "123,\"45\"\"321,,36\",789,,";
        csvParser.clean();
        List<List<String>> res9 = csvParser.parse(csv9);
        Assert.assertEquals("has more than five columns", 5, res9.get(0).size());
        Assert.assertArrayEquals("contents are not same", res9.get(0).toArray(), new String[]{"123", "45\"321,,36", "789", null, null});
    }
}
