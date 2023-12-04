const d = document
const urlMain = "http://localhost:8080/search/main?";
const urlCategory = "http://localhost:8080/search/category?";
const urlSetMainFeatured = "http://localhost:8080/admin/news/main/";
var searchUrl;

const $changeMain = d.getElementById("change-main-featured")

const $searchBtn = d.getElementById("searchBtn")
const $clearSearchBtn = d.getElementById("clearSearchBtn")
const $mainSearchInput = d.querySelectorAll(".mainSearch")
const $mainSearchList = d.getElementById("mainSearchList")
const $mainFragment = d.createDocumentFragment();
const $mainBtn = d.getElementById("mainBtn")
const $categoryBtn = d.querySelectorAll(".categoryBtn");

$categoryBtn.forEach(btn => {
    btn.addEventListener("click", () => {
       searchUrl = `${urlCategory}categoryId=${btn.id}&`

    })
})

$mainBtn.addEventListener("click", () => {
    searchUrl = urlMain
})

$searchBtn.addEventListener("click", async (e) => {
    e.preventDefault()
    let urlParams = searchUrl;
    let maxIndex = $mainSearchInput.length;

    $mainSearchInput.forEach((input, index) => {
        urlParams += `${input.name}=${input.value}`
        console.log(urlParams)
        if (index < (maxIndex -1)) urlParams += '&'
    });
    searchMainNews(urlParams)
})



async function searchMainNews(url) {
    try {

        let res = await fetch(
            url,
            { method: 'GET', mode: 'cors', headers: {"Content-type":"application/json"}}
            )

        let json = await res.json()
        console.log(json)

        if (!res.ok) {
            console.log(res)
            throw { status: res.status, statusText: res.statusText}  
        } 

        json.forEach(el => {
            let $tr = d.createElement("tr")
            let $category = d.createElement("th")
            let $newsTitle = d.createElement("td")
            let $newsAuthor = d.createElement("td")
            let $newsDate = d.createElement("td")
            let $setBtn = d.createElement("td")

            $category.innerText = el.newsCategory
            $newsTitle.innerText = el.newsTitle
            $newsAuthor.innerText = el.reporterName
            $newsDate.innerText = el.newsDate
            $setBtn.innerHTML = `<a class="btn btn-primary setMainNewsBtn" href="${urlSetMainFeatured}${el.newsId}">Seleccionar</a>`


            $tr.appendChild($category)
            $tr.appendChild($newsTitle)
            $tr.appendChild($newsAuthor)
            $tr.appendChild($newsDate)
            $tr.appendChild($setBtn)

            $mainFragment.appendChild($tr)
            })
        $mainSearchList.appendChild($mainFragment)
    } catch (error) {
        alert("error: " + error)

    } 
}
