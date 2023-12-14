const d = document

const $imageChange = d.getElementById("imgChange")

const $imagePreviewContainer = d.querySelector(".image-preview-container")
const imageId = $imagePreviewContainer.id

const $image = d.querySelector(".image-tag")

var $deleteBtn = d.getElementById(imageId === "" ? "delete-img-preview" : "delete-img")

var $inputImg = d.getElementById("archive")
const $inputContainer = $inputImg.parentNode

if (imageId != '') {
    getNewsImage($image.src)
}

$inputImg.addEventListener("change", inputChange)

$deleteBtn.addEventListener("click", () => {
    if (imageId === '') {
        $image.src = ""
        $deleteBtn.classList.toggle("visually-hidden")        
    } else {
        $imageChange.value = false
        $image.src = localStorage.getItem("image")
    }
    $inputContainer.innerHTML = ""
    let $newInput = createInputImage()
    $newInput.addEventListener("change", inputChange)
    $inputContainer.appendChild($newInput)
    $imagePreviewContainer.classList.toggle("mb-3")

})

function inputChange(e) {
    $imageChange.value = true
    const files = e.target.files
    const dataURL = window.URL.createObjectURL(files[0])
    $image.src = dataURL
    if (imageId === '') {
        $deleteBtn.classList.toggle("visually-hidden")
    }
    $imagePreviewContainer.classList.toggle("mb-3")
}

async function getNewsImage(url) {
    const res = await fetch(url)
    const blob = await res.blob()
    const imageDataURL = window.URL.createObjectURL(blob)
    localStorage.setItem("image", imageDataURL)
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

/*
$inputImg.addEventListener("change", (e) => {
    $imgChange.value = "true"
    const files = e.target.files
    const dataURL = window.URL.createObjectURL(files[0])
    if($newsImg != null ) {
        $newsImg.src = dataURL
    }
    if ($imgPreview != null) {
        $imgPreview.src = dataURL
    }
})

if ($deletePreview != null) {
    $deletePreview.addEventListener("click", ()=>{
        $imgPreview.src = ""
        $inputContainer.innerHTML = ""
        $inputImg = createInputImage()
        $inputImg.addEventListener("change", (e) => {
     
        const files = e.target.files
        const dataURL = window.URL.createObjectURL(files[0])
        $newsImg.src = dataURL
        if ($imgPreview != null) {
            $imgPreview.src = dataURL
        }
    })    
    })
}

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
*/