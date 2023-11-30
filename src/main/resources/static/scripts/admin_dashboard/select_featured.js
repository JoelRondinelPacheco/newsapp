const d = document
const $mainBtn = d.querySelectorAll(".selectMain")
const $categoryBtn = d.querySelectorAll(".selectByCategory")
const $modalBody = d.querySelector(".modal-body") 

$mainBtn.forEach($btn => {
    $btn.addEventListener("click", (e) => {
        let $container = $btn.parentNode.parentNode
        console.log($container)
        console.log($container.id)
        let $h3 = d.createElement("h3")
        $h3.innerText = `Titulo: ${$btn.dataset.title}. Autor: ${$btn.dataset.author}`
        $modalBody.appendChild($h3)
    })
})