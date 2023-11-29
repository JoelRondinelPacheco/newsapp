const d = document

const $searchBtn = d.getElementById("searchBtn")
const $clearSearchBtn = d.getElementById("clearSearchBtn")
const $mainSearchInput = d.querySelectorAll(".mainSearch")

//fetch con los datos

// Implementar busqueda con los datos: Controller, service, repository

$searchBtn.addEventListener("click", (e) => {
    e.preventDefault()
    
    $mainSearchInput.forEach(input => {
        console.log(input.value)
    });
})