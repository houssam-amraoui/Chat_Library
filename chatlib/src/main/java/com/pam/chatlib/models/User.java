package com.pam.chatlib.models;


import java.io.Serializable;

public class User implements Serializable {
    String name;
    String pic;
    String Token;

   /* public static final String UID = "uid";
    public static final String KEY_CREATED_AT = "createdAt";
    public static final String KEY_UPDATED_AT = "updatedAt";

    private static final String HAS_PASSWORD = "has_password";
    public static final String COL_GENDER = "gender";
    public static final String GENDER_MALE = "male";
    public static final String GENDER_FEMALE = "female";
    public static final String GENDER_BOTH = "both";

    public static final String STATUS_ALL = "all";
    public static final String STATUS_ONLINE = "online";
    public static final String STATUS_NEW = "new";

    private static final String COL_INSTALLATION = "installation";
    public static final String COL_ID = "objectId";
    private static final String COL_FULL_NAME = "name";
    private static final String COL_FIRST_NAME = "first_name";
    private static final String COL_BIO = "bio";
    public static final String COL_USERNAME = "username";
    private static final String COL_POPULARITY = "popularity";
    private static final String COL_EMAIL = "email";
    private static final String COL_EMAIL_VERIFIED = "emailVerified";
    private static final String COL_PHOTO_POSITION = "avatar_position";
    public static final String COL_PHOTOS = "photos";
    public static final String COL_GEO_POINT = "geopoint";
    public static final String COL_HAS_GEO_POINT = "hasGeopoint";
    public static final String COL_BIRTHDATE = "birthday";
    private static final String COL_LOCATION = "location";
    private static final String COL_CITY = "city";
    private static final String COL_LAST_ONLINE = "lastOnline";
    public static final String COL_AGE = "age";
    private static final String COL_ABOUT_ME = "aboutMe";

    // Filter preferences
    private static final String PREF_LOCATION_TYPE = "prefLocationType";
    //public static final String PREF_LOCATION = "prefLocation";
    private static final String PREF_GENDER = "prefGender";
    private static final String PREF_STATUS = "prefStatus";
    private static final String PREF_MIN_AGE = "prefMinAge";
    private static final String PREF_MAX_AGE = "prefMaxAge";

    // Vip and Premium features

    private static final String PREMIUM_LIFETIME = "premium_lifetime";
    private static final String PREMIUM = "premium";
    private static final String CREDIT = "credit";
    private static final String TOKENS = "tokens";

    private static final String VIP_ADS_DISABLED = "AdsDisabled";
    private static final String VIP_3X_POPULAR = "3xpopular";
    private static final String VIP_SHOW_ONLINE = "showOnline";
    private static final String VIP_EXTRA_SHOWS = "extraShows";
    public static final String VIP_MORE_VISITS = "getMoreVisits";
    public static final String VIP_MOVE_TO_TOP = "moveToTop";

    private static final String VIP_INVISIBLE_MODE = "invisibleMode";

    private static final String VIP_IS_INVISIBLE = "isInvisible";

    // Instagram
    private static final String INSTAGRAM_ID = "instaId";
    private static final String INSTAGRAM_LINK = "instaLink";
    private static final String INSTAGRAM_TOKEN = "instaToken";


    // Facebook
    private static final String FACEBOOK_ID = "fbId";
    private static final String FACEBOOK_LINK = "fbLink";

    // Privacy
    private static final String PRIVACY_SHOW_DISTANCE = "privacyShowDistance";
    private static final String PRIVACY_SHOW_ONLINE_STATUS = "privacyShowOnlineStatus";


    // Invisible mode
    public static final String PRIVACY_ALMOST_INVISIBLE = "privacyAlmostInvisible";
    private static final String PRIVACY_CLOAKED_INVISIBILITY = "privacyCloakedInvisibility";
    private static final String PRIVACY_HIDE_PREMIUM_STATUS = "privacyHidePremiumStatus";

    // Notifications
    private static final String PUSH_NOTIFICATIONS_MASSAGES = "pushMessages";
    private static final String PUSH_NOTIFICATIONS_MATCHES = "pushMatches";
    private static final String PUSH_NOTIFICATIONS_LIKED_YOU = "pushLikedYou";
    private static final String PUSH_NOTIFICATIONS_PROFILE_VISITOR = "pushProfileVisitor";
    private static final String PUSH_NOTIFICATIONS_FAVORITED_YOU = "pushFavoritedYou";
    private static final String PUSH_NOTIFICATIONS_DATOO_LIVE = "pushLive";

    public static final String BLOCKED_USERS = "blockedUsers";


    // Profile Edit
    private static final String RELATIONSHIP = "profile_relationship";
    public static final String RELATIONSHIP_COMPLICATED = "CP";
    public static final String RELATIONSHIP_SINGLE = "SG";
    public static final String RELATIONSHIP_TAKEN = "TK";

    private static final String SEXUALITY = "profile_sexuality";
    public static final String SEXUALITY_BISEXUAL = "BS";
    public static final String SEXUALITY_LESBIAN = "LB";
    public static final String SEXUALITY_ASK_ME = "AM";
    public static final String SEXUALITY_STRAIGHT = "ST";

    private static final String HEIGHT = "profile_body_height";

    public static final String BODY_TYPE = "profile_boty_type";
    public static final String BODY_TYPE_ATHLETIC = "AT";
    public static final String BODY_TYPE_AVERAGE = "AV";
    public static final String BODY_TYPE_FEW_EXTRA_POUNDS = "EP";
    public static final String BODY_TYPE_MUSCULAR = "ML";
    public static final String BODY_TYPE_BIG_AND_BEAUTIFUL = "BB";
    public static final String BODY_TYPE_SLIM = "SL";

    private static final String LIVING = "profile_living";
    public static final String LIVING_BY_MYSELF = "MS";
    public static final String LIVING_STUDENT_DORMITORY = "SD";
    public static final String LIVING_WITH_PARENTS = "PR";
    public static final String LIVING_WITH_PARTNER = "PN";
    public static final String LIVING_WITH_ROOMMATES = "RM";

    private static final String KIDS = "profile_kids";
    public static final String KIDS_GROWN_UP = "GU";
    public static final String KIDS_ALREADY_HAVE = "AH";
    public static final String KIDS_NO_NOVER = "NN";
    public static final String KIDS_SOMEDAY = "SY";

    private static final String SMOKING = "profile_smoking";
    public static final String SMOKING_IAM_A_HEAVY_SMOKER = "ES";
    public static final String SMOKING_I_HATE_SMOKING = "HS";
    public static final String SMOKING_I_DO_NOT_LIKE_IT = "DL";
    public static final String SMOKING_IAM_A_SOCIAL_SMOKER = "SM";
    public static final String SMOKING_I_SMOKE_OCCASIONALLY = "OC";

    private static final String DRINKING = "profile_drinking";
    public static final String DRINKING_I_DRINK_SOCIALLY = "DS";
    public static final String DRINKING_I_DO_NOT_DRINK = "DD";
    public static final String DRINKING_IAM_AGAINST_DRINKING = "AD";
    public static final String DRINKING_I_DRINK_A_LOT = "DT";

    private static final String LANGUAGE = "profile_language";

    private static final String WHAT_I_WANT = "profile_honestly_want";
    public static final String WHAT_I_WANT_JUST_TO_CHAT = "JC";
    public static final String WHAT_I_WANT_SOMETHING_CASUAL = "SC";
    public static final String WHAT_I_WANT_SOMETHING_SERIOUS = "SS";
    public static final String WHAT_I_WANT_LET_SEE_WHAT_HAPPENS = "WH";

    public static final String LIVE_STREAMS_COUNT = "live_stream_counter";
    private static final String LIVE_STREAMS_VIEWERS_COUNT = "live_viewers_counter";
    private static final String LIVE_STREAMS_VIEWERS = "live_viewers";
    private static final String LIVE_FOLLOWERS_COUNT = "live_followers_counter";*/


    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
