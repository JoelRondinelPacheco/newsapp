const d = document

const $editBtn = d.querySelectorAll('.btn-modal')
const $closeBtn = d.querySelectorAll('.modal-close')
const $saveBtn = d.getElementById('modal-save')

const $modalBody = d.getElementById('modal-body')
const $name = d.getElementById('modal-user-name')
const $email = d.getElementById('modal-user-email')
const $role = d.getElementById('modal-current-rol')
const $modalRolesMenu = d.getElementById('modal-roles-menu')
const $rolesList = d.getElementById('roles-list')
const $roleDefault = d.getElementById('role-default')

const rolesUrl = "http://localhost:8080/utils/roles"
var roles;

var $listRolesFragment = d.createDocumentFragment()

const toPascalCase = str =>
  str
    .match(/[A-Z]{2,}(?=[A-Z][a-z]+[0-9]*|\b)|[A-Z]?[a-z]+[0-9]*|[A-Z]|[0-9]+/g)
    .map(x => x.charAt(0).toUpperCase() + x.slice(1).toLowerCase())
    .join('');


async function roles (url) {
    try {
    const res = await fetch(url)
    const json = await res.json();
    console.log(json)
    roles = json
    } catch (err) {
        console.log("Error: " + err);
    }
}

roles(rolesUrl)

$editBtn.forEach(btn => {
    btn.addEventListener("click", (e) => {
        let role = toPascalCase(e.currentTarget.getAttribute('data-role'))

        console.log(role)
        $name.innerHTML = e.currentTarget.getAttribute('data-name')
        $email.innerHTML = e.currentTarget.getAttribute('data-email')
        $role.innerHTML = role

        roles.forEach(r => {
            if (r != role) {
                /*let $li = d.createElement("li")
                let $a = d.createElement("a")
                $a.classList.add("dropdown-item")
                $a.innerHTML = r
                $li.appendChild($a)
                $listRolesFragment.appendChild($li)*/
                let $option = d.createElement("option")
                $option.value = r
                $option.innerHTML = r
                $listRolesFragment.appendChild($option)
            }
        })

        if (role != "User") {
            let $text = d.createElement("p")
            $text.innerHTML = "No es usuario"
            $modalBody.appendChild($text)
        }
        $modalRolesMenu.appendChild($listRolesFragment)

    })
})


$closeBtn.addEventListener("click", function() {
    $name.innerText = ''
    $role.innerText = ''
    $rolesList.innerHTML = ''


})

