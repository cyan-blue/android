package so.xiaolu.xiaolu.mainsetting;

public class MainUrl {

    public String URL_BASE = "http://m.xiaolu.so/rest/v1/";
    public String URL_0 = "http://192.168.1.39:8000/rest/v1/";

    public String LOGIN_URL = URL_BASE + "register/customer_login";
    public String TODAY_URL = URL_BASE + "products/promote_today.json";


    //public String TODAY_URL = URL_0 + "products/promote_today.json";
//   public String TODAY_URL = URL_0 + "users.json";


    public String BROWSE_URL = URL_BASE + "/xueZhang/servlet/AndroidBroSev";
    public String IMAGE_URL = URL_BASE + "/xueZhang/";
    public String CONTENT_DETAIL = URL_BASE + "/xueZhang/servlet/AndroidBookDetailSev";
    public String DETAIL_IAMGE_URL = URL_BASE + "/xueZhang/";
    public String COLLECTION_URL = URL_BASE + "/xueZhang/servlet/AndroidCollectionSev";



    public String FAVORITE_IMAGE = URL_BASE + "/xueZhang/";

    public String SETTINGMYINFO_URL = URL_BASE + "/xueZhang/servlet/AndroidInfoSev";

    public String SETTINGREGISTER_URL = URL_BASE + "/xueZhang/servlet/AndroidRegisterSev";

    public String COMMODITY_COLLECTION_URL = URL_BASE + "/xueZhang/servlet/AndroidAddCollectionSev";

    public String getCOMMODITY_COLLECTION_URL() {
        return COMMODITY_COLLECTION_URL;
    }

    public String getSETTINGRESGISTER_URL() {
        return SETTINGREGISTER_URL;
    }

    public String getSETTINGMYINFO_URL() {
        return SETTINGMYINFO_URL;
    }

    public String getTODAY_URL() {
        return TODAY_URL;
    }

    public String getFAVORITE_IMAGE() {
        return FAVORITE_IMAGE;
    }

    public String getCOLLECTION_URL() {
        return COLLECTION_URL;
    }

    public String getCONTENT_DETAIL() {
        return CONTENT_DETAIL;
    }

    public void setCONTENT_DETAIL(String cONTENT_DETAIL) {
        CONTENT_DETAIL = cONTENT_DETAIL;
    }

    public String getDETAIL_IAMGE_URL() {
        return DETAIL_IAMGE_URL;
    }

    public void setDETAIL_IAMGE_URL(String dETAIL_IAMGE_URL) {
        DETAIL_IAMGE_URL = dETAIL_IAMGE_URL;
    }


    public String getLOGIN_URL() {
        return LOGIN_URL;
    }

    public void setLOGIN_URL(String lOGIN_URL) {
        LOGIN_URL = lOGIN_URL;
    }

    public String getBROWSE_URL() {
        return BROWSE_URL;
    }

    public void setBROWSE_URL(String bROWSE_URL) {
        BROWSE_URL = bROWSE_URL;
    }

    public String getIMAGE_URL() {
        return IMAGE_URL;
    }


}