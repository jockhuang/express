/**
 * 
 */
package biz.qianyan.search.express.query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Jock
 */
public class HighLighter {

    private static final Log log = LogFactory.getLog(HighLighter.class);

    private String           color;

    private int              length;

    public String highlight(String source, String query, boolean cut) {
        query = query.replaceAll(" && ", " ");
        query = query.replaceAll(" \\|\\| ", " ");
        log.debug("word=" + query);
        String senre[] = query.split("\\s");
        String strRe = ""; // for return String.
        if (cut) {
            int tmpPos = 0;
            int endPos = 0;
            int intPos = source.indexOf(senre[0]);
            if (intPos != -1) {
                tmpPos = (intPos - 30) < 0 ? 0 : (intPos - 30);
            }
            else {
                tmpPos = 0;
            }
            endPos = (tmpPos + length) > source.length() ? source.length() : (tmpPos + length);
            tmpPos = (endPos - length) < 0 ? 0 : (endPos - length);

            strRe = source.substring(tmpPos, endPos);
            strRe = strRe + "...";

        }
        else {
            strRe = source;
        }

        for (int i = 0; i < senre.length; i++) {
            // System.out.println("senre[i].trim()="+senre[i].trim());
            if ((!"|".equals(senre[i].trim())) && (!"-".equals(senre[i].trim()))
                    && (!"".equals(senre[i].trim()))) {
                strRe = strRe.replaceAll(senre[i], "<font color=\"" + color + "\">" + senre[i]
                        + "</font>");
            }
        }

        return strRe;
    }

    /**
     * @param color
     *            the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @param length
     *            the length to set
     */
    public void setLength(int length) {
        this.length = length;
    }

}
