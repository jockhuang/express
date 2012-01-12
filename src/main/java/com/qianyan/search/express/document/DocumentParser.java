/**
 * Lucene Document 、搜索结果及数据库记录的相互转化
 */
package com.qianyan.search.express.document;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.document.DateTools;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;

import com.qianyan.search.db.model.BusiOrder;
import com.qianyan.search.db.model.VwSearchComs;
import com.qianyan.search.db.model.VwSearchProduct;
import com.qianyan.search.db.model.VwSearchSupply;

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
                    edoc.setMessageurl("http://www.qianyan.biz/message.do?n=" + doc.get("id")
                            + "&c=" + doc.get("title"));

            } catch (Exception e) {
                edoc.setMessageurl("http://www.qianyan.biz/message.do?n=" + doc.get("id") + "&c="
                        + doc.get("title"));
            }

        }
        else {
            edoc.setMessageurl("http://www.qianyan.biz/message.do?n=" + doc.get("id") + "&c="
                    + doc.get("title"));
        }

        return edoc;
    }

    public static ReleatedKeyword convertReleated(Document doc) {
        ReleatedKeyword edoc = new ReleatedKeyword();

        edoc.setKeyword(doc.get("keyword"));

        return edoc;
    }

    public static ExpressDocument convert(Document doc) {
        log.debug("convert to ExpressDocument");
        ExpressDocument edoc = new ExpressDocument();
        edoc.setId(Integer.parseInt(doc.get("id")));
        edoc.setTitle(doc.get("title"));
        edoc.setOrititle(doc.get("title"));
        edoc.setBrief(doc.get("brief"));
        edoc.setKeywords(doc.get("keywords"));
        edoc.setUrl(doc.get("url"));
        String str = doc.get("picurl");
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
        edoc.setComid(Integer.parseInt(doc.get("comid")));

        edoc.setBuilddate(doc.get("builddate"));

        edoc.setItemname(doc.get("itemname"));
        edoc.setContent(doc.get("content"));
        edoc.setComurl(doc.get("comurl"));
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
                    edoc.setMessageurl("http://www.qianyan.biz/message.do?n=" + doc.get("id")
                            + "&c=" + doc.get("title"));

            } catch (Exception e) {
                edoc.setMessageurl("http://www.qianyan.biz/message.do?n=" + doc.get("id") + "&c="
                        + doc.get("title"));
            }

        }
        else {
            edoc.setMessageurl("http://www.qianyan.biz/message.do?n=" + doc.get("id") + "&c="
                    + doc.get("title"));
        }
        str = doc.get("type");

        try {
            int type = Integer.parseInt(str);
            if (type == 1) {
                str = doc.get("infotype");
                if (Short.parseShort(str) == 2) {
                    type = 4;
                }
            }
            edoc.setType(type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            edoc.setCreatedate(DateTools.stringToDate(doc.get("createdate")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.debug("convert end");
        return edoc;
    }

    private static float getBoost(long date) {
        float boost = 1F;
        long now = System.currentTimeMillis();
        int days = (int) (now - date) / (1000 * 60 * 60 * 24);
        if (days < 31) {
            boost = 10;
        }
        else if (days < 180) {
            boost = 5f;
        }
        else if (days < 365) {
            boost = 2f;
        }
        else if (days > 731) {
            boost = 0.1F;
        }
        return boost;
    }

    public static Document parse(ExpressDocument sdoc) {
        log.debug("parse to lucene document");
        Document doc = new Document();
        doc
                .add(new Field("id", sdoc.getId().toString(), Field.Store.YES,
                        Field.Index.NOT_ANALYZED));
        doc.add(new Field("url", sdoc.getUrl(), Field.Store.YES, Field.Index.ANALYZED));
        Field brief = new Field("brief", sdoc.getBrief() == null ? "" : sdoc.getBrief(),
                Field.Store.YES, Field.Index.ANALYZED,
                Field.TermVector.WITH_POSITIONS_OFFSETS);
        brief.setBoost(1.5F);
        doc.add(brief);
        Field title = new Field("title", sdoc.getTitle() == null ? "" : sdoc.getTitle(),
                Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS);
        title.setBoost(4F);
        doc.add(title);
        Field keywords = new Field("keywords",
                sdoc.getKeywords() == null ? "" : sdoc.getKeywords(), Field.Store.NO,
                Field.Index.ANALYZED);
        keywords.setBoost(2F);
        doc.add(keywords);
        doc.add(new Field("itemname", sdoc.getItemname(), Field.Store.YES, Field.Index.ANALYZED));
        doc.add(new Field("address", sdoc.getAddress() == null ? "" : sdoc.getAddress(),
                Field.Store.NO, Field.Index.ANALYZED));
        doc.add(new Field("classkey", sdoc.getClasskey() == null ? "" : sdoc.getClasskey(),
                Field.Store.YES, Field.Index.NOT_ANALYZED));
        // doc.add(new Field("classname", , Field.Store.YES, Field.Index.UN_TOKENIZED));
        doc.add(new Field("comurl", sdoc.getComurl() == null ? "" : sdoc.getComurl(),
                Field.Store.YES, Field.Index.ANALYZED));
        doc.add(new Field("contact", sdoc.getContact() == null ? "" : sdoc.getContact(),
                Field.Store.NO, Field.Index.ANALYZED));
        // doc.add(new Field("content", sdoc.getContent(), Field.Store.NO, Field.Index.TOKENIZED));
        doc.add(new Field("email", sdoc.getEmail() == null ? "" : sdoc.getEmail(), Field.Store.YES,
                Field.Index.ANALYZED));
        String fax = sdoc.getFaxarea() == null ? "" : sdoc.getFaxarea();

        fax = fax + " " + (sdoc.getFax() == null ? "" : sdoc.getFax());
        fax = fax.trim();
        doc.add(new Field("fax", fax, Field.Store.YES, Field.Index.ANALYZED));
        doc.add(new Field("mobile", sdoc.getMobile() == null ? "" : sdoc.getMobile(),
                Field.Store.NO, Field.Index.ANALYZED));
        doc.add(new Field("msncode", sdoc.getMsncode() == null ? "" : sdoc.getMsncode(),
                Field.Store.NO, Field.Index.ANALYZED));
        doc.add(new Field("picurl", sdoc.getPicurl(), Field.Store.YES, Field.Index.NOT_ANALYZED));

        doc.add(new Field("price", sdoc.getPrice(), Field.Store.YES, Field.Index.NO));
        doc.add(new Field("comid", sdoc.getComid() + "", Field.Store.YES, Field.Index.NO));
        doc.add(new Field("qqcode", sdoc.getQqcode() == null ? "" : sdoc.getQqcode(),
                Field.Store.NO, Field.Index.ANALYZED));
        doc.add(new Field("zip", sdoc.getZipcode() == null ? "" : sdoc.getZipcode(),
                Field.Store.NO, Field.Index.ANALYZED));
        doc.add(new Field("address", sdoc.getAddress() == null ? "" : sdoc.getAddress(),
                Field.Store.NO, Field.Index.ANALYZED));
        doc.add(new Field("regionname", sdoc.getRegionname() == null ? "" : sdoc.getRegionname(),
                Field.Store.YES, Field.Index.ANALYZED));
        doc.add(new Field("itemurl", sdoc.getItemurl() == null ? "" : sdoc.getItemurl(),
                Field.Store.YES, Field.Index.ANALYZED));
        doc.add(new Field("regionkey", sdoc.getRegionkey() == null ? "" : sdoc.getRegionkey(),
                Field.Store.YES, Field.Index.NOT_ANALYZED));
        doc.add(new Field("quantity", sdoc.getQuantity(), Field.Store.YES, Field.Index.NO));
        doc.add(new Field("route", sdoc.getRoute() == null ? "" : sdoc.getRoute(), Field.Store.NO,
                Field.Index.ANALYZED));
        doc.add(new Field("fullpath", sdoc.getFullPath() == null ? "" : sdoc.getFullPath(),
                Field.Store.YES, Field.Index.NOT_ANALYZED));
        String tel = sdoc.getPhonearea() == null ? "" : sdoc.getPhonearea();
        tel = tel + " " + (sdoc.getTel() == null ? "" : sdoc.getTel());
        tel = tel.trim();

        doc.add(new Field("tel", tel, Field.Store.YES, Field.Index.ANALYZED));
        // doc.add(new Field("search", sdoc.getTitle() + sdoc.getBrief() + sdoc.getKeywords() +
        // sdoc.getContent(), Field.Store.NO, Field.Index.TOKENIZED));
        doc.add(new Field("createdate", DateTools.timeToString(sdoc.getCreatedate().getTime(),
                                                               DateTools.Resolution.MINUTE),
                Field.Store.YES, Field.Index.NOT_ANALYZED));
        doc.add(new Field("mainmode", sdoc.getMainmode() + "", Field.Store.YES,
                Field.Index.NOT_ANALYZED_NO_NORMS));
        doc.add(new Field("type", sdoc.getType() + "", Field.Store.YES, Field.Index.NOT_ANALYZED));
        doc.add(new Field("infotype", sdoc.getInfotype() + "", Field.Store.YES,
                Field.Index.NOT_ANALYZED_NO_NORMS));
        doc.add(new Field("builddate", sdoc.getBuilddate() == null ? "" : sdoc.getBuilddate(),
                Field.Store.YES, Field.Index.NOT_ANALYZED));
        doc.setBoost(getBoost(sdoc.getCreatedate().getTime()));
        log.debug("parse end");
        return doc;
    }

    public static Document parse(AdDocument sdoc) {
        log.debug("parse to lucene document");
        Document doc = new Document();
        doc
                .add(new Field("id", sdoc.getId().toString(), Field.Store.YES,
                        Field.Index.NOT_ANALYZED));
        doc.add(new Field("url", sdoc.getUrl() == null ? "" : sdoc.getUrl(), Field.Store.YES,
                Field.Index.NO));
        doc.add(new Field("itemname", sdoc.getItemname() == null ? "" : sdoc.getItemname(),
                Field.Store.YES, Field.Index.NO));
        doc.add(new Field("itemurl", sdoc.getItemurl() == null ? "" : sdoc.getItemurl(),
                Field.Store.YES, Field.Index.NO));
        doc.add(new Field("title", sdoc.getTitle(), Field.Store.YES, Field.Index.NO));
        doc.add(new Field("brief", sdoc.getBrief(), Field.Store.YES, Field.Index.NO));
        doc.add(new Field("picurl", sdoc.getPicurl(), Field.Store.YES, Field.Index.NO));
        doc.add(new Field("adtype", sdoc.getAdtype() + "", Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));
        doc.add(new Field("searchtype", sdoc.getSearchtype() + "", Field.Store.YES,
                Field.Index.NOT_ANALYZED_NO_NORMS));
        doc.add(new Field("infotype", sdoc.getInfotype() + "", Field.Store.YES,
                Field.Index.NOT_ANALYZED_NO_NORMS));
        doc.add(new Field("type", sdoc.getType() + "", Field.Store.YES, Field.Index.NO));
        doc.add(new Field("comid", sdoc.getComid() + "", Field.Store.YES, Field.Index.NO));
        doc.add(new Field("comurl", sdoc.getComurl() == null ? "" : sdoc.getComurl(),
                Field.Store.YES, Field.Index.NO));
        doc.add(new Field("rank", sdoc.getRank() == null ? "0" : sdoc.getRank().toString(),
                Field.Store.YES, Field.Index.NOT_ANALYZED));
        String key = sdoc.getKeyword();
        if ((key == null || "".equals(key.trim()))) {
            key = "jock";
        }
        log.info("keyword=" + key);
        Field keyword = new Field("keyword", key, Field.Store.YES, Field.Index.ANALYZED);
        doc.add(keyword);
        doc.add(new Field("date", DateTools.timeToString(sdoc.getCreatedate().getTime(),
                                                         DateTools.Resolution.MINUTE),
                Field.Store.YES, Field.Index.NOT_ANALYZED));
        doc.add(new Field("createdate", DateTools.timeToString(System.currentTimeMillis(),
                                                               DateTools.Resolution.MINUTE),
                Field.Store.YES, Field.Index.NOT_ANALYZED));
        doc.add(new Field("fullpath", sdoc.getFullPath() == null ? "" : sdoc.getFullPath(),
                Field.Store.YES, Field.Index.NO));
        doc.add(new Field("regionname", sdoc.getRegionname() == null ? "" : sdoc.getRegionname(),
                Field.Store.YES, Field.Index.NO));

        log.debug("parse end");
        return doc;
    }

    public static Document parse(ReleatedKeyword rk) {
        log.debug("parse to lucene document");
        Document doc = new Document();
        Field keyword = new Field("keyword", rk.getKeyword(), Field.Store.YES,
                Field.Index.ANALYZED);
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
