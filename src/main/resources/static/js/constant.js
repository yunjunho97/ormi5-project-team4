import {
    getAdoptionStatus,
    getApproveStatus,
    getPageId,

} from "./utils.js"

export const URL = "http://localhost:8080";

// API 영역
export const API_LOGIN = "/auth/login";
export const API_LOGOUT = '/auth/logout';
export const API_NOTICE = '/notice';
export const API_ADMIN_GET_POSTS = "/admin/post";
export const API_MY_INFO = "/auth/this";

// 페이지 영역
export const HOME = '/home';
export const MANAGE_POST = '/manage/posts';
export const READ_POST = '/read-post/'; // pathVariable 이 항상 있음
export const MY_PAGE = '/mypage';
export const ADMIN_PAGE = '/manage/posts';
export const ADMIN_NOTICE = '/manage/notice';
export const ADMIN_USER = '/manage/member';
export const NOTICE_LIST = '/notice-list';

// Parameter 영역
export const PAGE_ID = getPageId();
export const PAGE_ID_PREVIOUS = PAGE_ID - 1;
export const PAGE_ID_NEXT = PAGE_ID + 1;
export const APPROVE_STATUS = getApproveStatus();
export const ADOPTION_STATUS = getAdoptionStatus();