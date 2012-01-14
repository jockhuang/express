/**
 * Lucene Document 、搜索结果及数据库记录的相互转化
 */
package biz.qianyan.search.express.document;

import java.util.Date;
import java.util.zip.DataFormatException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.document.CompressionTools;
import org.apache.lucene.document.DateTools;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumericField;
import org.apache.lucene.search.highlight.Highlighter;

import biz.qianyan.search.db.model.BusiOrder;
import biz.qianyan.search.db.model.VwSearchComs;
import biz.qianyan.search.db.model.VwSearchProduct;
import biz.qianyan.search.db.model.VwSearchSupply;

/**
 * @author Jock
 */
public final class DocumentParser {

    private static final Log log = LogFactory.getLog(DocumentParser.class);

    public static AdDocument convertad(Document doc) {
        AdDocument edoc = new AdDocument();
        edoc.setId(Integer.parseInt(doc.get("id")));
        edoc.setTitle(doc.get("title"));
        String brief = doc.get("brief");
        if (brief.length() > 78) {
            brief = brief.substring(0, 78) + "...";
        }

        edoc.setBrief(brief);
        edoc.setKeyword(doc.get("keyword"));
        edoc.setUrl(doc.get("url"));
        String str = doc.get("picurl");
        if (str == null || str.length() == 0) {
            str = "images/2-8.jpg";
        }
        edoc.setPicurl(str);
        edoc.setItemname(doc.get("itemname"));
        edoc.setItemurl(doc.get("itemurl"));
        edoc.setType(Short.parseShort(doc.get("type")));
        edoc.setAdtype(Short.parseShort(doc.get("adtype")));
        edoc.setRegionname(doc.get("regionname"));
        edoc.setComurl(doc.get("comurl"));
        edoc.setFullPath(doc.get("fullpath"));
        str = doc.get("comid");
        try {
            edoc.setCreatedate(DateTools.stringToDate(doc.get("createdate")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (str != null && str.trim().length() > 0) {
            try {
                int comid = Integer.parseInt(str);
                if (comid > 0)
                    edoc.setMessageurl("http://www.qianyan.biz/qy/item.do?i=7&yid=" + str);
                else
                    edoc.setMessageurl("http://www.qianyan.biz/message.do?n=" + doc.get("id") + "&c="
                            + doc.get("title"));

            } catch (Exception e) {
                edoc.setMessageurl("http://www.qianyan.biz/message.do?n=" + doc.get("id") + "&c=" + doc.get("title"));
            }

        } else {
            edoc.setMessageurl("http://www.qianyan.biz/message.do?n=" + doc.get("id") + "&c=" + doc.get("title"));
        }

        return edoc;
    }

    public static ReleatedKeyword convertReleated(Document doc) {
        ReleatedKeyword edoc = new ReleatedKeyword();

        edoc.setKeyword(doc.get("keyword"));

        return edoc;
    }

    public static ExpressDocument convert(Document doc) throws DataFormatException {
        log.debug("convert to ExpressDocument");
        ExpressDocument edoc = new ExpressDocument();

        edoc.setId(((NumericField) doc.getFieldable("id")).getNumericValue().intValue());
        
        edoc.setTitle(doc.get("title"));
        edoc.setOrititle(doc.get("title"));
        edoc.setBrief(doc.get("brief"));
        edoc.setKeywords(doc.get("keywords"));
        edoc.setUrl(CompressionTools.decompressString(doc.getBinaryValue("url")));
        String str;
        try{
            str = CompressionTools.decompressString(doc.getBinaryValue("picurl"));
        }catch(Exception e){
            str = null;
        }
        if (str == null || str.length() == 0) {
            str = "images/2-8.jpg";
        }
        edoc.setPicurl(str);
        str = doc.get("price");
        if (str == null || str.trim().length() == 0) {
            str = "0.00";
        }
        if (str.length() > 30) {
            str = str.substring(0, 30);
        }
        edoc.setPrice(str);
        str = doc.get("quantity");
        if (str == null || str.trim().length() == 0) {
            str = "0";
        }
        edoc.setQuantity(str);
        edoc.setComid(((NumericField) doc.getFieldable("comid")).getNumericValue().intValue());

        edoc.setBuilddate(doc.get("builddate"));

        edoc.setItemname(doc.get("itemname"));
        edoc.setContent(doc.get("content"));
        edoc.setComurl(CompressionTools.decompressString(doc.getBinaryValue("comurl")));
        edoc.setRegionname(doc.get("regionname"));
        edoc.setRegionkey(doc.get("regionkey"));
        edoc.setClasskey(doc.get("classkey"));
        edoc.setFullPath(doc.get("fullpath"));
        edoc.setBuilddate(doc.get("builddate"));
        str = doc.get("comid");
        if (str.trim().length() > 0) {
            try {
                int comid = Integer.parseInt(str);
                if (comid > 0)
                    edoc.setMessageurl("http://www.qianyan.biz/qy/item.do?i=7&yid=" + str);
                else
                    edoc.setMessageurl("http://www.qianyan.biz/message.do?n=" + doc.get("id") + "&c="
                            + doc.get("title"));

            } catch (Exception e) {
                edoc.setMessageurl("http://www.qianyan.biz/message.do?n=" + doc.get("id") + "&c=" + doc.get("title"));
            }

        } else {
            edoc.setMessageurl("http://www.qianyan.biz/message.do?n=" + doc.get("id") + "&c=" + doc.get("title"));
        }

        int type = ((NumericField) doc.getFieldable("type")).getNumericValue().intValue();
        if (type == 1) {

            if (((NumericField) doc.getFieldable("infotype")).getNumericValue().intValue() == 2) {
                type = 4;
            }
        }
        edoc.setType(type);

        edoc.setCreatedate(new Date(((NumericField) doc.getFieldable("createdate")).getNumericValue().longValue()));

        log.debug("convert end");
        return edoc;
    }

    private static float getBoost(long date) {
        float boost = 1F;
        long now = System.currentTimeMillis();
        int days = (int) (now - date) / (1000 * 60 * 60 * 24);
        if (days < 31) {
            boost = 10;
        } else if (days < 180) {
            boost = 5f;
        } else if (days < 365) {
            boost = 2f;
        } else if (days > 731) {
            boost = 0.1F;
        }
        return boost;
    }

    public static Document parse(ExpressDocument sdoc) {
        log.debug("parse to lucene document");
        Document doc = new Document();
        doc.add(new NumericField("id", Field.Store.YES, true).setIntValue(sdoc.getId()));

        doc.add(new Field("url", CompressionTools.compressString(sdoc.getUrl())));
        Field brief = new Field("brief", sdoc.getBrief() == null ? "" : sdoc.getBrief(), Field.Store.YES,
                Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS);
        brief.setBoost(1.5F);
        doc.add(brief);
        Field title = new Field("title", sdoc.getTitle() == null ? "" : sdoc.getTitle(), Field.Store.YES,
                Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS);
        title.setBoost(4F);
        doc.add(title);

        doc.add(new Field("itemname", sdoc.getItemname(), Field.Store.YES, Field.Index.ANALYZED));

        doc.add(new Field("classkey", sdoc.getClasskey() == null ? "" : sdoc.getClasskey(), Field.Store.YES,
                Field.Index.NOT_ANALYZED_NO_NORMS));
        // doc.add(new Field("classname", , Field.Store.YES, Field.Index.UN_TOKENIZED));
        doc.add(new Field("comurl", CompressionTools.compressString(sdoc.getComurl() == null ? "" : sdoc.getComurl())));
        doc.add(new Field("contact",
                CompressionTools.compressString(sdoc.getContact() == null ? "" : sdoc.getContact())));
        // doc.add(new Field("content", sdoc.getContent(), Field.Store.NO, Field.Index.TOKENIZED));

        doc.add(new Field("picurl", sdoc.getPicurl(), Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));

        doc.add(new Field("price", sdoc.getPrice(), Field.Store.YES, Field.Index.NO));
        doc.add(new NumericField("comid", Field.Store.YES, true).setIntValue(sdoc.getComid()));

        doc.add(new Field("regionname", sdoc.getRegionname() == null ? "" : sdoc.getRegionname(), Field.Store.YES,
                Field.Index.ANALYZED_NO_NORMS));
        doc.add(new Field("itemurl",
                CompressionTools.compressString(sdoc.getItemurl() == null ? "" : sdoc.getItemurl())));
        doc.add(new Field("regionkey", sdoc.getRegionkey() == null ? "" : sdoc.getRegionkey(), Field.Store.YES,
                Field.Index.NOT_ANALYZED_NO_NORMS));
        doc.add(new Field("quantity", sdoc.getQuantity(), Field.Store.YES, Field.Index.NO));
        doc.add(new Field("route", sdoc.getRoute() == null ? "" : sdoc.getRoute(), Field.Store.NO,
                Field.Index.ANALYZED_NO_NORMS));
        doc.add(new Field("fullpath", sdoc.getFullPath() == null ? "" : sdoc.getFullPath(), Field.Store.YES,
                Field.Index.NOT_ANALYZED_NO_NORMS));

        // doc.add(new Field("search", sdoc.getTitle() + sdoc.getBrief() + sdoc.getKeywords() +
        // sdoc.getContent(), Field.Store.NO, Field.Index.TOKENIZED));
        doc.add(new NumericField("createdate", Field.Store.YES, true).setLongValue(sdoc.getCreatedate().getTime()));
        doc.add(new NumericField("mainmode", Field.Store.YES, true).setIntValue(sdoc.getMainmode()));
        doc.add(new NumericField("type", Field.Store.YES, true).setLongValue(sdoc.getType()));
        doc.add(new NumericField("infotype", Field.Store.YES, true).setLongValue(sdoc.getInfotype()));

        doc.add(new Field("builddate", sdoc.getBuilddate() == null ? "" : sdoc.getBuilddate(), Field.Store.YES,
                Field.Index.NOT_ANALYZED_NO_NORMS));
        doc.setBoost(getBoost(sdoc.getCreatedate().getTime()));
        log.debug("parse end");
        return doc;
    }

    public static Document parse(AdDocument sdoc) {
        log.debug("parse to lucene document");
        Document doc = new Document();
        doc.add(new NumericField("id").setIntValue(sdoc.getId()));

        doc.add(new Field("url", sdoc.getUrl() == null ? "" : sdoc.getUrl(), Field.Store.YES, Field.Index.NO));
        doc.add(new Field("itemname", sdoc.getItemname() == null ? "" : sdoc.getItemname(), Field.Store.YES,
                Field.Index.NO));
        doc.add(new Field("itemurl", sdoc.getItemurl() == null ? "" : sdoc.getItemurl(), Field.Store.YES,
                Field.Index.NO));
        doc.add(new Field("title", sdoc.getTitle(), Field.Store.YES, Field.Index.NO));
        doc.add(new Field("brief", sdoc.getBrief(), Field.Store.YES, Field.Index.NO));
        doc.add(new Field("picurl", sdoc.getPicurl(), Field.Store.YES, Field.Index.NO));
        doc.add(new NumericField("adtype").setIntValue(sdoc.getAdtype()));
        doc.add(new NumericField("searchtype").setIntValue(sdoc.getSearchtype()));
        doc.add(new NumericField("infotype").setIntValue(sdoc.getInfotype()));
        doc.add(new NumericField("type").setIntValue(sdoc.getType()));
        doc.add(new NumericField("comid").setIntValue(sdoc.getComid()));

        doc.add(new Field("comurl", sdoc.getComurl() == null ? "" : sdoc.getComurl(), Field.Store.YES, Field.Index.NO));
        doc.add(new NumericField("rank").setIntValue(sdoc.getRank()));

        String key = sdoc.getKeyword();
        if ((key == null || "".equals(key.trim()))) {
            key = "jock";
        }
        log.info("keyword=" + key);
        Field keyword = new Field("keyword", key, Field.Store.YES, Field.Index.ANALYZED_NO_NORMS);
        doc.add(keyword);
        doc.add(new Field("date", DateTools.timeToString(sdoc.getCreatedate().getTime(), DateTools.Resolution.MINUTE),
                Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));
        doc.add(new Field("createdate",
                DateTools.timeToString(System.currentTimeMillis(), DateTools.Resolution.MINUTE), Field.Store.YES,
                Field.Index.NOT_ANALYZED_NO_NORMS));
        doc.add(new Field("fullpath", sdoc.getFullPath() == null ? "" : sdoc.getFullPath(), Field.Store.YES,
                Field.Index.NO));
        doc.add(new Field("regionname", sdoc.getRegionname() == null ? "" : sdoc.getRegionname(), Field.Store.YES,
                Field.Index.NO));

        log.debug("parse end");
        return doc;
    }

    public static Document parse(ReleatedKeyword rk) {
        log.debug("parse to lucene document");
        Document doc = new Document();
        Field keyword = new Field("keyword", rk.getKeyword(), Field.Store.YES, Field.Index.ANALYZED_NO_NORMS);
        doc.add(keyword);
        log.debug("parse end");
        return doc;
    }

    public static ExpressDocument tranfer(VwSearchComs record) throws Exception {
        ExpressDocument doc = new ExpressDocument();
        BeanUtils.copyProperties(doc, record);
        // doc.setCompanyname(record.getItemname());
        if (record.getRegionname() == null) {
            doc.setRegionname("");
        }
        if (record.getItemname() == null) {
            doc.setItemname("");
        }
        if (record.getContent() == null) {
            doc.setContent("");
        }
        doc.setType(2);
        return doc;
    }

    public static ExpressDocument tranfer(VwSearchProduct record) throws Exception {
        ExpressDocument doc = new ExpressDocument();
        BeanUtils.copyProperties(doc, record);
        // doc.setItemname(record.getItemname());
        if (record.getRegionname() == null) {
            doc.setRegionname("");
        }
        if (record.getItemname() == null) {
            doc.setItemname("");
        }
        if (record.getContent() == null) {
            doc.setContent("");
        }
        doc.setType(1);
        return doc;
    }

    public static ExpressDocument tranfer(VwSearchSupply record) throws Exception {
        ExpressDocument doc = new ExpressDocument();
        BeanUtils.copyProperties(doc, record);

        if (record.getRegionname() == null) {
            doc.setRegionname("");
        }
        if (record.getItemname() == null) {
            doc.setItemname("");
        }
        if (record.getContent() == null) {
            doc.setContent("");
        }
        doc.setType(3);
        return doc;
    }

    public static AdDocument tranfer(BusiOrder record) throws Exception {
        AdDocument doc = new AdDocument();
        BeanUtils.copyProperties(doc, record);
        doc.setComid(record.getYid());
        doc.setFullPath(record.getClassname());
        doc.setRegionname(record.getProvince());
        doc.setAdtype(record.getType());
        doc.setItemname(record.getCompanyname());
        doc.setUrl(record.getItemurl());
        return doc;
    }
    // private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
}
