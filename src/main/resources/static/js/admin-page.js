const URL = "http://localhost:8080";
document.addEventListener("DOMContentLoaded", function() {
    const pageId = getPageId();

    // 게시글 받아오기
    fetch(`/admin/post/${pageId}`)
        .then(response => response.json())
        .then(data => {
            const postList = document.getElementById('post-list');
            data.forEach(post => {
                const listItem = document.createElement('li');
                    listItem.className = 'container-post-img'
                    const div = document.createElement('div');
                        div.className = 'image-container'
                        const input = document.createElement('input');
                            input.type = 'checkbox';
                            input.className = 'checkbox';
                        const img = document.createElement('img');
                            img.alt = '이미지';
                    const status = document.createElement("p");
                        status.textContent = getResponseForAdoptionStatus(post.adoptionStatus);
                    const title = document.createElement('a');
                        // todo : title.href = 'http://localhost:8080/'
                        title.textContent = post.title;

                div.appendChild(input);
                div.appendChild(img);
                listItem.appendChild(div);
                listItem.appendChild(status);
                listItem.appendChild(title);

                postList.appendChild(listItem);

            });
        })
        .catch(error => console.error('Error:', error));

    // 게시글 페이지 개수 생성
    fetch('/admin/post')
        .then(response => response.json())
        .then(data => {
            const itemCount = data.length;
            const numOfLists = Math.ceil(itemCount / 6);
            const itemList = document.getElementById('post-pages');

            // 이전 페이지 바로가기 이미지
            const previousLi = document.createElement('li');
                const goPreviousPage = document.createElement('a');
                    const previousPageImg = document.createElement('img');
                        previousPageImg.src = '/images/page-previous.svg';
                        previousPageImg.alt = '이전 페이지'
                if(!(pageId === '1')){
                    goPreviousPage.href = URL + `/manage/posts/` + (parseInt(pageId) - 1).toString();
                }

            goPreviousPage.appendChild(previousPageImg);
            previousLi.appendChild(goPreviousPage);
            itemList.appendChild(goPreviousPage);

            // 페이지 생성
            for (let i = 0; i < numOfLists; i++) {
                const li = document.createElement('li');
                    const pageElement = document.createElement(
                        pageId === (i + 1).toString() ? 'p' : 'a'
                    )
                    pageElement.textContent = (i + 1).toString();
                    if(pageElement.tagName.toLowerCase() === 'p'){
                        pageElement.className = 'font-page-selected';
                    }
                    else{
                        pageElement.className = 'font-page';
                        pageElement.href = URL + `/manage/posts/` + (i + 1).toString();
                    }

                li.appendChild(pageElement);
                itemList.appendChild(li);
            }

            // 다음 페이지 바로가기 이미지
            const nextLi = document.createElement('li');
                const goNextPage = document.createElement('a');
                    const nextPageImg = document.createElement('img');
                        nextPageImg.src = '/images/page-next.svg';
                        nextPageImg.alt = '다음 페이지'
                if(!(pageId === numOfLists.toString())){
                    goNextPage.href = URL + `/manage/posts/` + (parseInt(pageId) + 1).toString();
                }

            goNextPage.appendChild(nextPageImg);
            nextLi.appendChild(goNextPage);
            itemList.appendChild(goNextPage);

        })
        .catch(error => console.error('Error:', error));
});

function getResponseForAdoptionStatus(adoptionStatus) {
    switch(adoptionStatus) {
        case 'POSTING':
            return '[ 진행중인 공고 ]';
        case 'PROCEEDING':
            return '[ 진행중인 공고 ]';
        case 'ADOPTED':
            return '[ 완료된 공고 ]';
    }
}

function getPageId(){
    const pathSegments = window.location.pathname.split('/');
    return pathSegments[pathSegments.length - 1];
}