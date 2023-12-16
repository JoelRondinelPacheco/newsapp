const d = document

const $test = d.getElementById("test")
const $form = d.querySelector('form');
const $bodyInput = d.getElementById("body")

const quill = new Quill('#editor', {
    theme: 'snow'
});

$form.addEventListener("submit", (e) => {
    let html = quill.root.innerHTML
    $bodyInput.value = html
    $form.submit()
})