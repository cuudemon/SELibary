package com.sevnc.selibrary.ads;

import java.util.List;

/**
 * Created by QuangVan on 29/12/2017.
 */

public class AdsItems {


    /**
     * status : ok
     * error_code : 0
     * developer : Software Evolution
     * message : Success!
     * items : [{"cat_int":"2","category":"Books & Reference","app_type":"2","type":"Tiện ích","package_name":"com.se.doctruyenma","title":"Truyện ma","icon":"https://lh3.googleusercontent.com/2Wn6PbKmSFvqxRm4DOgSAPiTbmyZ_SerVpncqHvDES1OH7NEt6wKK_SMTTzWPT3S51Zc=w300","ads_banner":"https://i.imgur.com/KCoTm3P.jpg","ads_priority_banner":"https://i.imgur.com/uSjO1Hm.jpg","i18n_lang":"2","developer":"SEsoft","created":"4/2017","market_url":"market://details?id=com.se.doctruyenma","priority":"1"}]
     */

    private String status;
    private int error_code;
    private String developer;
    private String message;
    private List<ItemsBean> items;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }



    public static class ItemsBean {
        /**
         * cat_int : 2
         * category : Books & Reference
         * app_type : 2
         * type : Tiện ích
         * package_name : com.se.doctruyenma
         * title : Truyện ma
         * icon : https://lh3.googleusercontent.com/2Wn6PbKmSFvqxRm4DOgSAPiTbmyZ_SerVpncqHvDES1OH7NEt6wKK_SMTTzWPT3S51Zc=w300
         * ads_banner : https://i.imgur.com/KCoTm3P.jpg
         * ads_priority_banner : https://i.imgur.com/uSjO1Hm.jpg
         * i18n_lang : 2
         * developer : SEsoft
         * created : 4/2017
         * market_url : market://details?id=com.se.doctruyenma
         * priority : 1
         */

        private String cat_int;
        private String category;
        private String app_type;
        private String type;
        private String package_name;
        private String title;
        private String icon;
        private String ads_banner;
        private String ads_priority_banner;
        private String i18n_lang;
        private String developer;
        private String created;
        private String market_url;
        private String priority;

        public ItemsBean(String cat_int, String category, String app_type, String type, String package_name, String title, String icon, String ads_banner, String ads_priority_banner, String i18n_lang, String developer, String created, String market_url, String priority) {
            this.cat_int = cat_int;
            this.category = category;
            this.app_type = app_type;
            this.type = type;
            this.package_name = package_name;
            this.title = title;
            this.icon = icon;
            this.ads_banner = ads_banner;
            this.ads_priority_banner = ads_priority_banner;
            this.i18n_lang = i18n_lang;
            this.developer = developer;
            this.created = created;
            this.market_url = market_url;
            this.priority = priority;
        }

        public String getCat_int() {
            return cat_int;
        }

        public void setCat_int(String cat_int) {
            this.cat_int = cat_int;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getApp_type() {
            return app_type;
        }

        public void setApp_type(String app_type) {
            this.app_type = app_type;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPackage_name() {
            return package_name;
        }

        public void setPackage_name(String package_name) {
            this.package_name = package_name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getAds_banner() {
            return ads_banner;
        }

        public void setAds_banner(String ads_banner) {
            this.ads_banner = ads_banner;
        }

        public String getAds_priority_banner() {
            return ads_priority_banner;
        }

        public void setAds_priority_banner(String ads_priority_banner) {
            this.ads_priority_banner = ads_priority_banner;
        }

        public String getI18n_lang() {
            return i18n_lang;
        }

        public void setI18n_lang(String i18n_lang) {
            this.i18n_lang = i18n_lang;
        }

        public String getDeveloper() {
            return developer;
        }

        public void setDeveloper(String developer) {
            this.developer = developer;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getMarket_url() {
            return market_url;
        }

        public void setMarket_url(String market_url) {
            this.market_url = market_url;
        }

        public String getPriority() {
            return priority;
        }

        public void setPriority(String priority) {
            this.priority = priority;
        }
    }
}
