package com.akamon.api.client.proxy;

public class ErrorCodes {
    /**
     * Service definition errors
     */
    public static final int BAD_SERVICE_DEFINITION_ERROR = 201;
    public static final int BAD_SERVICE_NOT_EXISTS = 202;
    public static final int BAD_SERVICE_REQUEST_NOT_ALLOWED = 203;

    public static final int SERVICE_NEEDS_MORE_PARAMETERS = 210;

    /*
     * Validation errors
     */
    public static final int BAD_PARAMETER_ERROR = 301;
    public static final int BAD_PARAMETER_REQUIRED = 302;
    public static final int BAD_PARAMETER_TYPE = 303;
    public static final int BAD_PARAMETER_REGEXP = 304;
    public static final int BAD_PARAMETER_DUPLICATED = 305;

    /**
     * Service response errors
     */
    public static final int BAD_RESPONSE_ERROR_GENERIC = 400;
    public static final int BAD_RESPONSE_ERROR_JSON = 401;

    public static final int UNAUTHORIZED_USER = 450;
    public static final int INVALID_PUBLIC_USER_ID = 451;

    /**
     * Service internal error
     */
    public static final int INTERNAL_SERVICE_ERROR = 501;

    /**
     * Internal errors
     */
    public static final int GENERIC_INTERNAL_ERROR = 600;

    /**
     * Providers errors
     */
    public static final int PROVIDER_NOT_FOUND = 700;

    public static final int PROVIDEREXCHANGE_NOT_FOUND = 750;
    public static final int PROVIDEREXCHANGE_INVALID = 751;

    /**
     * Clients applications errors
     */
    public static final int BAD_APPLICATION_NOT_ALLOWED = 800;
    public static final int BAD_APPLICATION_EXPIRED_TIMESTAMP = 801;


    /**
     * Single Sign-On error
     */
    public static final int SSO_PARAMETERS_VALIDATION = 900;

    /**
     * Games service error
     */
    public static final int GAME_INVALID = 1000;
    public static final int GAME_INVALID_MATCH = 1001;
    public static final int GAME_INVALID_CHIPS = 1002;
    public static final int GAME_LEVEL_INVALID = 1003;

    /**
     * Session service error
     */
    public static final int SESSION_ALREADY_EXISTS = 1100;
    public static final int SESSION_NOT_EXISTS = 1101;

    /**
     * Notification service error
     */
    public static final int NOTIFICATIONS_INVALID_FORMAT = 1201;
    public static final int NOTIFICATIONS_INVALID_EVENT = 1202;
    public static final int NOTIFICATIONS_INVALID_CONTENT = 1204;

    public static final int NOTIFICATIONS_INVALID_ACCESS = 1205;
    public static final int NOTIFICATION_INVALID = 1206;

    /**
     * User service error codes
     */
    public static final int USER_GENERIC_ERROR = 1300;
    public static final int USER_NOT_ENOUGH_CHIPS = 1301;
    public static final int USER_ACCOUNT_CAN_NOT_BE_LINKED = 1302;
    public static final int USER_INVALID = 1303;
    public static final int USER_DISABLED = 1304;
    public static final int USER_GAME_TOKEN_INVALID = 1305;

    /**
     * Experiments error
     */
    public static final int EXPERIMENT_INVALID = 1400;

    /**
     * Offer
     */
    public static final int OFFER_INVALID                = 1500;
    public static final int OFFER_ENROLLED_ERROR         = 1501;
    public static final int OFFER_INVALID_TOKEN          = 1502;
    public static final int OFFER_INVALID_USER_FOR_TOKEN = 1503;
    public static final int OFFER_USED                   = 1504;

    /**
     * Context
     */
    public static final int CONTEXT_INVALID = 1600;

    /**
     * Product errors
     */
    public static final int PRODUCT_INVALID = 1700;

    /**
     * Order errors
     */
    public static final int ORDER_INVALID = 1800;
    public static final int ORDER_PROVIDER_REFERENCE = 1801;
}
