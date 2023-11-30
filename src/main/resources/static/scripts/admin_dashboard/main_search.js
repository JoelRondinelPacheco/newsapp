const d = document
const urlMain = "http://localhost:8080/search/main?";

const $searchBtn = d.getElementById("searchBtn")
const $clearSearchBtn = d.getElementById("clearSearchBtn")
const $mainSearchInput = d.querySelectorAll(".mainSearch")
const $mainSearchList = d.getElementById("mainSearchList")
const $mainFragment = d.createDocumentFragment();

$searchBtn.addEventListener("click", async (e) => {
    e.preventDefault()
    let urlParams = urlMain;
    let maxIndex = $mainSearchInput.length;
    console.log(maxIndex)
    $mainSearchInput.forEach((input, index) => {
        urlParams += `${input.name}=${input.value}`
        if (index < (maxIndex -1)) urlParams += '&'
    });
    searchMainNews(urlParams)
})

async function searchMainNews(url) {
    try {
        console.log("try")
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
            $category.innerText = el.newsCategory
            $newsTitle.innerText = el.newsTitle
            $newsAuthor.innerText = el.reporterName
            $tr.appendChild($category)
            $tr.appendChild($newsTitle)
            $tr.appendChild($newsAuthor)

            $mainFragment.appendChild($tr)
            })
        $mainSearchList.appendChild($mainFragment)
    } catch (error) {
        alert("error: " + error)

    } 
}
