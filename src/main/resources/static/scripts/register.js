const d = document

const $imageContainer = d.getElementById("input-container")
const $defaultImage = d.getElementById("default-profile")
const urlImage = $defaultImage.src

var $imgInput = d.getElementById("archive")
const $deleteBtn = d.getElementById("profile-img-delete")

getDefaultImage(urlImage)

const reader = new FileReader()
reader.onload = function (e) {
$image.src = e.target.result
}


$imgInput.addEventListener("change", (e) => {
    const files = e.target.files
    const dataURL = window.URL.createObjectURL(files[0])
    $defaultImage.src = dataURL
})

$deleteBtn.addEventListener("click", () => {
    $defaultImage.src = localStorage.getItem("defaultImage")
    $imageContainer.innerHTML = ""
    $imgInput = createInputImage()
    $imgInput.addEventListener("change", (e) => {
        const files = e.target.files
        const dataURL = window.URL.createObjectURL(files[0])
        $defaultImage.src = dataURL
    })
    $imageContainer.appendChild($imgInput)
    
})

async function getDefaultImage(url) {
    const res = await fetch(url)
    const blob = await res.blob()
    const imageDataURL = window.URL.createObjectURL(blob)
    localStorage.setItem("defaultImage", imageDataURL)
}

function createInputImage() {
    $input = d.createElement("input")
    $input.type = "file"
    $input.id = "archive"
    $input.name="archive"
    $input.accept = "image/jpeg"
    $input.classList.add("form-control")
    return $input
}