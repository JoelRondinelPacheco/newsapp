const d = document
const $mainBtn = d.querySelectorAll(".selectMain")
const $categoryBtn = d.querySelectorAll(".selectByCategory")

const $modalBody = d.querySelector(".modal-body") 
const $modalTitle = d.querySelector(".modal-title")

const $cancelBtn = d.getElementById("cancelBtn")
const $confirmBtn = d.getElementById("confirmBtn")
const $closeBtn = d.getElementById("closeBtn")

const urlSetMainFeatured = "http://localhost:8080/admin/news/main/";
const urlSetCategoryFeatured = "http://localhost:8080/admin/news/category/";


$mainBtn.forEach($btn => {
    $btn.addEventListener("click", (e) => {
        let $container = $btn.parentNode.parentNode
        let $h4 = d.createElement("h4")
        $h4.innerText = `Titulo: ${$btn.dataset.title}. Autor: ${$btn.dataset.author}`
        $modalBody.appendChild($h4)
        $modalTitle.innerHTML = `Noticia principal`
        $confirmBtn.setAttribute("href", urlSetMainFeatured + $container.id)
    })
})

$categoryBtn.forEach($btn => {
    $btn.addEventListener("click", () => {
        let $container = $btn.parentNode.parentNode
        let $h4 = d.createElement("h4")
        $h4.innerText = `Titulo: ${$btn.dataset.title}. Autor: ${$btn.dataset.author}`
        $modalBody.appendChild($h4)
        $modalTitle.innerHTML = `Noticia principal por categoria`
        $confirmBtn.setAttribute("href", urlSetCategoryFeatured + $container.id)
    }) 
})

$cancelBtn.addEventListener("click", () => {
    $modalBody.innerHTML = ""
    $confirmBtn.removeAttribute("href")
})

$closeBtn.addEventListener("click", () => {
    $modalBody.innerHTML = ""
    $confirmBtn.removeAttribute("href")
})