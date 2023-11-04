const $image = document.getElementById("image")
const $imgInput = document.getElementById("archive")
const $btn = document.getElementById("btndelete")

const reader = new FileReader()
reader.onload = function (e) {
$image.src = e.target.result
}

$btn.addEventListener("click", () => {
$image.src="http://localhost:8080/publicimg"
})

$imgInput.addEventListener("change" , () => {
if ($imgInput.files && $imgInput.files[0]) {

    reader.readAsDataURL($imgInput.files[0]);
}
})