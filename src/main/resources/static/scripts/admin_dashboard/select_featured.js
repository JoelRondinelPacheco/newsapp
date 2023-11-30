const d = document
const $mainBtn = d.querySelectorAll(".selectMain")
const $categoryBtn = d.querySelectorAll(".selectByCategory")
const $modalBody = d.querySelector(".modal-body") 
const $cancelBtn = d.getElementById("cancelBtn")
const $confirmBtn = d.getElementById("confirmBtn")
const $closeBtn = d.getElementById("closeBtn")

const mainUrl = "http://localhost:8080/news/set/main/"
const categoryUrl = "http://localhost:8080/news/set/category/"

$mainBtn.forEach($btn => {
    $btn.addEventListener("click", (e) => {
        let $container = $btn.parentNode.parentNode
        let $h4 = d.createElement("h4")
        $h4.innerText = `Titulo: ${$btn.dataset.title}. Autor: ${$btn.dataset.author}`
        $modalBody.appendChild($h4)
        $confirmBtn.setAttribute("href", mainUrl+ $container.id)
    })
})

$categoryBtn.forEach($btn => {
    $btn.addEventListener("click", () => {
        let $container = $btn.parentNode.parentNode
        let $h4 = d.createElement("h4")
        $h4.innerText = `Titulo: ${$btn.dataset.title}. Autor: ${$btn.dataset.author}`
        $modalBody.appendChild($h4)
        $confirmBtn.setAttribute("href", categoryUrl+ $container.id)
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