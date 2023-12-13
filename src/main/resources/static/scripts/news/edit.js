const d = document
const $deleteBtn = d.getElementById("delete-img")

const $newsImg = d.getElementById("news-img")
const $inputContainer = d.getElementById("img-input-container")
var $inputImg = d.getElementById("archive")
const $imgChange = d.getElementById("imgChange")

const urlImage = $newsImg.src
console.log(urlImage)
getNewsImage(urlImage)

$inputImg.addEventListener("change", (e) => {
    $imgChange.value = "true"
    const files = e.target.files
    const dataURL = window.URL.createObjectURL(files[0])
    $newsImg.src = dataURL
})

$deleteBtn.addEventListener("click", () => {
    $newsImg.src = localStorage.getItem("newsImage")
    $imgChange.value = "false"
    $inputContainer.innerHTML = ""
    $inputImg = createInputImage()
    $inputImg.addEventListener("change", (e) => {
        const files = e.target.files
        const dataURL = window.URL.createObjectURL(files[0])
        $newsImg.src = dataURL
    })

    $inputContainer.appendChild($inputImg)

})

async function getNewsImage(url) {
    const res = await fetch(url)
    const blob = await res.blob()
    const imageDataURL = window.URL.createObjectURL(blob)
    localStorage.setItem("newsImage", imageDataURL)
}

function createInputImage() {
    $input = d.createElement("input")
    $input.type = "file"
    $input.id = "archive"
    $input.name="image"
    $input.accept = "image/jpeg"
    $input.classList.add("form-control")
    return $input
}