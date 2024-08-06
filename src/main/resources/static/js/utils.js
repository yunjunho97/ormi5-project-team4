// export Function 영역
import {ADOPTION_STATUS, URL, HOME, PAGE_ID, API_NOTICE, API_MY_INFO, APPROVE_STATUS, ADMIN_PAGE} from "./constant.js";

export function getResponseForAdoptionStatus(adoptionStatus) {
    switch (adoptionStatus) {
        case 'POSTING':
            return '[ 진행중인 공고 ]';
        case 'PROCEEDING':
            return '[ 진행중인 공고 ]';
        case 'ADOPTED':
            return '[ 완료된 공고 ]';
    }
}

export function getResponseForUserRole(role) {
    switch (role) {
        case 'ADMIN':
            return '관리자';
        case 'ACTIVE':
            return '유저';
        case 'INACTIVE':
            return '차단된 유저';
    }
}

export function getResponseForUserRoleAdmin(role){
    switch (role) {
        case 'ACTIVE':
            return { text: '일반유저', className: 'member-status-normal' };
        case 'INACTIVE':
            return { text: '차단유저', className: 'member-status-banned' };
    }
}

export function calculatePagination(totalPages, currentPage, maxDisplayPages = 9) {
    if (totalPages <= maxDisplayPages) {
        return { startPage: 0, endPage: totalPages };
    }

    let startPage = Math.max(0, currentPage - Math.floor(maxDisplayPages / 2));
    let endPage = startPage + maxDisplayPages - 1;

    if (endPage > totalPages) {
        endPage = totalPages - 1;
        startPage = Math.max(0, endPage - maxDisplayPages + 1);
    }

    return { startPage, endPage };
}

export function setAdminNavigationInfo(
    pendingCategoryObj, pendingObj, approveCategoryObj, approvedObj, deniedCategoryObj, deniedObj) {
    setAdminNavigationFilter(pendingObj, approvedObj, deniedObj);
    setAdminNavigationCategoryStyle(pendingCategoryObj, pendingObj, approveCategoryObj, approvedObj, deniedCategoryObj, deniedObj);
}

function setAdminNavigationFilter(pendingObj, approvedObj, deniedObj) {
    switch (APPROVE_STATUS) {
        case 'PENDING':
            pendingObj.href = URL + ADMIN_PAGE + `?&page=0`;
            approvedObj.href = URL + ADMIN_PAGE + `?&page=0&approvestatus=APPROVED`;
            deniedObj.href = URL + ADMIN_PAGE + `?&page=0&approvestatus=DENIED`;
            break;
        case 'APPROVED':
            pendingObj.href = URL + ADMIN_PAGE + `?&page=0&approvestatus=PENDING`;
            approvedObj.href = URL + ADMIN_PAGE + `?&page=0`;
            deniedObj.href = URL + ADMIN_PAGE + `?&page=0&approvestatus=DENIED`;
            break;
        case 'DENIED':
            pendingObj.href = URL + ADMIN_PAGE + `?&page=0&approvestatus=PENDING`;
            approvedObj.href = URL + ADMIN_PAGE + `?&page=0&approvestatus=APPROVED`;
            deniedObj.href = URL + ADMIN_PAGE + `?&page=0`;
            break;
        default:
            pendingObj.href = URL + ADMIN_PAGE + `?&page=0&approvestatus=PENDING`;
            approvedObj.href = URL + ADMIN_PAGE + `?&page=0&approvestatus=APPROVED`;
            deniedObj.href = URL + ADMIN_PAGE + `?&page=0&approvestatus=DENIED`;
            break;
    }
}

function setAdminNavigationCategoryStyle(
    pendingCategoryObj, pendingObj, approveCategoryObj, approvedObj, deniedCategoryObj, deniedObj) {
    switch (APPROVE_STATUS) {
        case 'PENDING':
            pendingCategoryObj.className = 'container-nav-category-selected';
            pendingObj.className = 'font-nav-category-selected';
            approveCategoryObj.className = 'container-nav-category';
            approvedObj.className = 'font-nav-category';
            deniedCategoryObj.className = 'container-nav-category';
            deniedObj.className = 'font-nav-category';
            break;
        case 'APPROVED':
            pendingCategoryObj.className = 'container-nav-category';
            pendingObj.className = 'font-nav-category';
            approveCategoryObj.className = 'container-nav-category-selected';
            approvedObj.className = 'font-nav-category-selected';
            deniedCategoryObj.className = 'container-nav-category';
            deniedObj.className = 'font-nav-category'
            break;
        case 'DENIED':
            pendingCategoryObj.className = 'container-nav-category';
            pendingObj.className = 'font-nav-category';
            approveCategoryObj.className = 'container-nav-category';
            approvedObj.className = 'font-nav-category';
            deniedCategoryObj.className = 'container-nav-category-selected';
            deniedObj.className = 'font-nav-category-selected';
            break;
        default:
            pendingCategoryObj.className = 'container-nav-category';
            pendingObj.className = 'font-nav-category';
            approveCategoryObj.className = 'container-nav-category';
            approvedObj.className = 'font-nav-category';
            deniedCategoryObj.className = 'container-nav-category';
            deniedObj.className = 'font-nav-category'
    }
}

export function setNavigationFilter(postingObj, adoptedObj) {
    switch (ADOPTION_STATUS) {
        case 'POSTING':
            postingObj.href = URL + HOME + `?&page=${PAGE_ID}`;
            adoptedObj.href = URL + HOME + `?&page=${PAGE_ID}&adoptionstatus=ADOPTED`;
            break;
        case 'ADOPTED':
            postingObj.href = URL + HOME + `?&page=${PAGE_ID}&adoptionstatus=POSTING`;
            adoptedObj.href = URL + HOME + `?&page=${PAGE_ID}`;
            break;
        default:
            postingObj.href = URL + HOME + `?&page=${PAGE_ID}&adoptionstatus=POSTING`;
            adoptedObj.href = URL + HOME + `?&page=${PAGE_ID}&adoptionstatus=ADOPTED`;
            break;
    }
}

export function setNavigationCategoryStyle(postingCategoryObj, postingObj, adoptedCategoryObj, adoptedObj) {
    switch (ADOPTION_STATUS) {
        case 'POSTING':
            postingCategoryObj.className = 'container-nav-category-selected';
            postingObj.className = 'font-nav-category-selected';
            adoptedCategoryObj.className = 'container-nav-category';
            adoptedObj.className = 'font-nav-category'
            break;
        case 'ADOPTED':
            postingCategoryObj.className = 'container-nav-category';
            postingObj.className = 'font-nav-category';
            adoptedCategoryObj.className = 'container-nav-category-selected';
            adoptedObj.className = 'font-nav-category-selected'
            break;
        default:
            postingCategoryObj.className = 'container-nav-category';
            postingObj.className = 'font-nav-category';
            adoptedCategoryObj.className = 'container-nav-category';
            adoptedObj.className = 'font-nav-category'
            break;
    }
}

export function getImgSrc(data) {
    let imgSrc;
    try {
        if (!data || !data.images || !data.images[0] || !data.images[0].imgUrl) {
            throw new Error("Image data is not available");
        }
        imgSrc = data.images[0].imgUrl;
    } catch (e) {
        imgSrc = '/images/animal-test-img.svg';
    }

    return imgSrc;
}

function getUrlParameter(name) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(name) || '';
}

export function getPageId() {
    const pageParam = getUrlParameter('page');
    return (isNaN(pageParam) || pageParam === '') ? 0 : parseInt(pageParam, 10);
}

export function getApproveStatus() {
    return getUrlParameter('approvestatus') || '';
}

export function getAdoptionStatus() {
    return getUrlParameter('adoptionstatus') || '';
}

export async function getMyInfo() {
    try {
        const response = await fetch(URL + API_MY_INFO);
        if (!response.ok) {
            throw new Error(`HTTP 오류! 상태: ${response.status}`);
        }
        const data = await response.json();
        return data;
    } catch (error) {
        console.error('오류 발생:', error);
    }
}

export function getDateFormat(timestamp) {
    const date = new Date(timestamp);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 +1 필요
    const day = String(date.getDate()).padStart(2, '0');

    return `${year}.${month}.${day}`;
}

export function getEmail() {
    return getUrlParameter('email') || '';
}