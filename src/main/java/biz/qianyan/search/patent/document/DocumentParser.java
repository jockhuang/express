/**
 * 
 */
package biz.qianyan.search.patent.document;

import java.text.SimpleDateFormat;

import org.apache.lucene.document.DateTools;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;

import biz.qianyan.search.db.model.VwSearchPatent;

/**
 * @author Jock
 */
public class DocumentParser {
    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat df2 = new SimpleDateFormat("yyyy年MM月dd日");

    public static PatentDocument convert(Document doc) {
        PatentDocument pd = new PatentDocument();
        pd.setId(Integer.parseInt(doc.get("id")));
        pd.setApplyno(doc.get("applyno"));
        pd.setTitle(doc.get("title"));
        pd.setBrief(doc.get("brief"));
        pd.setRightrequest(doc.get("rightrequest"));
        pd.setInternationalno(doc.get("internationalno"));
        pd.setCategoryno(doc.get("categoryno"));
        pd.setProposer(doc.get("proposer"));
        pd.setCountry(doc.get("country").replaceAll("\\([^> ]*\\)", ""));
        pd.setAddress(doc.get("address"));
        pd.setZip(doc.get("zip"));
        pd.setAgent(doc.get("agent"));
        pd.setAgentorg(doc.get("agentorg"));
        pd.setAgentorgaddress(doc.get("agentorgaddress"));
        pd.setOriginator(doc.get("originator"));
        try {
            pd.setApplydate(df.format(DateTools.stringToDate(doc.get("applydate"))));
        } catch (Exception e) {
            try {
                pd.setApplydate(df2.format(DateTools.stringToDate(doc.get("applydate"))));
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }
        // pd.setApplydate(doc.get("applydate"));
        pd.setPublicno(doc.get("publicno"));
        pd.setPublicdate(doc.get("publicdate"));
        pd.setCommisionbulletindate(doc.get("commisionbulletindate"));
        pd.setBulletindate(doc.get("bulletindate"));
        pd.setCommisiondate(doc.get("commisiondate"));
        pd.setBulletinno(doc.get("bulletinno"));
        pd.setPriority(doc.get("priority"));
        pd.setApprovehistory(doc.get("approvehistory"));
        pd.setAttachedpicno(doc.get("attachedpicno"));
        pd.setPagenum(doc.get("pagenum"));
        pd.setAskrightnum(doc.get("askrightnum"));
        pd.setInterpublicno(doc.get("interpublicno"));
        pd.setInterpublicdate(doc.get("interpublicdate"));
        pd.setInterpubliclang(doc.get("interpubliclang"));
        pd.setInterapplyno(doc.get("interapplyno"));
        pd.setInterapplydate(doc.get("interapplydate"));
        pd.setInternationalizationdate(doc.get("internationalizationdate"));
        pd.setPct(doc.get("pct"));
        pd.setClasses(doc.get("classes"));
        return pd;
    }

    public static Document parser(PatentDocument p) throws Exception {
        Document doc = new Document();
        doc.add(new Field("id", p.getId() + "", Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));
        doc.add(new Field("applyno", p.getApplyno() == null ? "" : p.getApplyno(), Field.Store.YES,
                Field.Index.ANALYZED_NO_NORMS));
        doc.add(new Field("title", p.getTitle() == null ? "" : p.getTitle(), Field.Store.YES,
                Field.Index.ANALYZED_NO_NORMS));
        doc.add(new Field("brief", p.getBrief() == null ? "" : p.getBrief(), Field.Store.YES,
                Field.Index.ANALYZED_NO_NORMS));

        doc.add(new Field("rightrequest", p.getRightrequest() == null ? "" : p.getRightrequest(), Field.Store.YES,
                Field.Index.NO));
        doc.add(new Field("internationalno", p.getInternationalno() == null ? "" : p.getInternationalno(),
                Field.Store.YES, Field.Index.NO));
        doc.add(new Field("categoryno", p.getCategoryno() == null ? "" : p.getCategoryno(), Field.Store.YES,
                Field.Index.NO));
        doc.add(new Field("proposer", p.getProposer() == null ? "" : p.getProposer(), Field.Store.YES,
                Field.Index.ANALYZED_NO_NORMS));
        doc.add(new Field("country", p.getCountry() == null ? "" : p.getCountry(), Field.Store.YES,
                Field.Index.ANALYZED_NO_NORMS));
        doc.add(new Field("address", p.getAddress() == null ? "" : p.getAddress(), Field.Store.YES,
                Field.Index.ANALYZED_NO_NORMS));
        doc.add(new Field("zip", p.getZip() == null ? "" : p.getZip(), Field.Store.YES, Field.Index.NO));
        doc.add(new Field("agent", p.getAgent() == null ? "" : p.getAgent(), Field.Store.YES,
                Field.Index.ANALYZED_NO_NORMS));
        doc.add(new Field("agentorg", p.getAgentorg() == null ? "" : p.getAgentorg(), Field.Store.YES,
                Field.Index.ANALYZED_NO_NORMS));
        doc.add(new Field("agentorgaddress", p.getAgentorgaddress() == null ? "" : p.getAgentorgaddress(),
                Field.Store.YES, Field.Index.NO));
        doc.add(new Field("originator", p.getOriginator() == null ? "" : p.getOriginator(), Field.Store.YES,
                Field.Index.ANALYZED_NO_NORMS));
        long date = 0;
        try {
            date = df.parse(p.getApplydate()).getTime();
        } catch (Exception e) {
            date = df2.parse(p.getApplydate()).getTime();
        }
        doc.add(new Field("applydate", DateTools.timeToString(date, DateTools.Resolution.DAY), Field.Store.YES,
                Field.Index.NO));
        doc.add(new Field("publicno", p.getPublicno() == null ? "" : p.getPublicno(), Field.Store.YES, Field.Index.NO));
        doc.add(new Field("publicdate", p.getPublicdate() == null ? "" : p.getPublicdate(), Field.Store.YES,
                Field.Index.NO));
        doc.add(new Field("commisionbulletindate", p.getCommisionbulletindate() == null ? "" : p
                .getCommisionbulletindate(), Field.Store.YES, Field.Index.NO));
        doc.add(new Field("bulletindate", p.getBulletindate() == null ? "" : p.getBulletindate(), Field.Store.YES,
                Field.Index.NO));
        doc.add(new Field("commisiondate", p.getCommisiondate() == null ? "" : p.getCommisiondate(), Field.Store.YES,
                Field.Index.NO));
        doc.add(new Field("bulletinno", p.getBulletinno() == null ? "" : p.getBulletinno(), Field.Store.YES,
                Field.Index.NO));
        doc.add(new Field("priority", p.getPriority() == null ? "" : p.getPriority(), Field.Store.YES, Field.Index.NO));
        doc.add(new Field("approvehistory", p.getApprovehistory() == null ? "" : p.getApprovehistory(),
                Field.Store.YES, Field.Index.NO));
        doc.add(new Field("attachedpicno", p.getAttachedpicno() == null ? "" : p.getAttachedpicno(), Field.Store.YES,
                Field.Index.NO));
        doc.add(new Field("pagenum", p.getPagenum() == null ? "" : p.getPagenum(), Field.Store.YES, Field.Index.NO));
        doc.add(new Field("askrightnum", p.getAskrightnum() == null ? "" : p.getAskrightnum(), Field.Store.YES,
                Field.Index.NO));
        doc.add(new Field("interpublicno", p.getInterpublicno() == null ? "" : p.getInterpublicno(), Field.Store.YES,
                Field.Index.NO));
        doc.add(new Field("interpublicdate", p.getInterpublicdate() == null ? "" : p.getInterpublicdate(),
                Field.Store.YES, Field.Index.NO));
        doc.add(new Field("interpubliclang", p.getInterpubliclang() == null ? "" : p.getInterpubliclang(),
                Field.Store.YES, Field.Index.NO));

        doc.add(new Field("interapplyno", p.getInterapplyno() == null ? "" : p.getInterapplyno(), Field.Store.YES,
                Field.Index.NO));
        doc.add(new Field("interapplydate", p.getInterapplydate() == null ? "" : p.getInterapplydate(),
                Field.Store.YES, Field.Index.NO));
        doc.add(new Field("internationalizationdate", p.getInternationalizationdate() == null ? "" : p
                .getInternationalizationdate(), Field.Store.YES, Field.Index.NO));
        doc.add(new Field("pct", p.getPct() == null ? "" : p.getPct(), Field.Store.YES, Field.Index.NO));
        doc.add(new Field("classes", p.getClasses() == null ? "" : p.getClasses(), Field.Store.YES, Field.Index.NO));
        return doc;
    }

    public static PatentDocument transfer(VwSearchPatent p) {
        PatentDocument pd = new PatentDocument();
        pd.setId(p.getId());
        pd.setApplyno(p.getC1());
        if (pd.getApplyno().length() == 8) {
            pd.setClasses(pd.getApplyno().charAt(2) + "");
        } else {
            pd.setClasses(pd.getApplyno().charAt(4) + "");
        }
        pd.setTitle(p.getC2());
        pd.setBrief(p.getC3());
        pd.setRightrequest(p.getC4());
        pd.setInternationalno(p.getC5());
        pd.setCategoryno(p.getC6());
        pd.setProposer(p.getC7());
        pd.setCountry(p.getC8());
        pd.setAddress(p.getC9());
        pd.setZip(p.getC10());
        pd.setAgent(p.getC11());
        pd.setAgentorg(p.getC12());
        pd.setAgentorgaddress(p.getC13());
        pd.setOriginator(p.getC14());

        pd.setApplydate(p.getC15());
        pd.setPublicdate(p.getC17());
        pd.setPublicno(p.getC16());
        pd.setCommisionbulletindate(p.getC18());
        pd.setBulletindate(p.getC19());
        pd.setCommisiondate(p.getC20());
        pd.setBulletinno(p.getC21());
        pd.setPriority(p.getC22());
        pd.setApprovehistory(p.getC23());
        pd.setAttachedpicno(p.getC24());
        pd.setPagenum(p.getC25());
        pd.setAskrightnum(p.getC26());

        pd.setInterpublicno(p.getC27());
        pd.setInterpublicdate(p.getC28());
        pd.setInterpubliclang(p.getC29());
        pd.setInterapplyno(p.getC30());
        pd.setInterapplydate(p.getC31());
        pd.setInternationalizationdate(p.getC32());
        pd.setPct(p.getC33());

        return pd;
    }

    public static void main(String[] args) {
        System.out.println("北京(21)".replaceAll("\\([^> ]*\\)", ""));
    }
}
