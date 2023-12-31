const d = document

const $editBtn = d.querySelectorAll('.btn-modal')
const $closeBtn = d.querySelectorAll('.modal-close')
const $saveBtn = d.getElementById('modal-save')

const $modalBody = d.querySelector('.modal-body')
const $template = d.getElementById('modal-body-template')

var $rolesList
const $roleDefault = d.getElementById('role-default')

const rolesUrl = "http://localhost:8080/utils/roles"

var roles;

const toPascalCase = str =>
    str
        .match(/[A-Z]{2,}(?=[A-Z][a-z]+[0-9]*|\b)|[A-Z]?[a-z]+[0-9]*|[A-Z]|[0-9]+/g)
        .map(x => x.charAt(0).toUpperCase() + x.slice(1).toLowerCase())
        .join('');


async function roles(url) {
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

        const clone = d.importNode($template.content, true)
        const salary = e.currentTarget.getAttribute('data-salary')

        const $name = clone.getElementById('modal-user-name')
        const $email = clone.getElementById('modal-user-email')
        const $role = clone.getElementById('modal-current-rol')
        const $salaryContainer = clone.getElementById('modal-salary')
        const $modalRolesMenu = clone.getElementById('modal-roles-menu')
        const $form = clone.getElementById("roleForm")
        const userId = e.currentTarget.getAttribute("data-id")

        $name.innerHTML = e.currentTarget.getAttribute('data-name')
        $email.innerHTML = e.currentTarget.getAttribute('data-email')
        $role.innerHTML = role


        roles.forEach(r => {
                let $option = d.createElement("option")
                $option.value = r
                $option.innerHTML = r
                $option.selected = (role === r)
                $modalRolesMenu.appendChild($option)

    
        })

        if (role != "User") {
            $salaryContainer.innerHTML = createSalary(salary)
        }

        $modalBody.appendChild(clone)

        $rolesList = d.getElementById('modal-roles-menu')
        $rolesList.addEventListener("change", (e) => {
            console.log($rolesList.value)
            $form.action = `/role/edit/${userId}`

            if ($rolesList.value == "User") {
                $salaryContainer.innerHTML = ''
            } else {
                $salaryContainer.innerHTML = createSalary(salary)
            } 
            

        })
        
    })
})

function createSalary(salary) {
    return `<span class="input-group-text">Salary</span>
    <input type="text" name="salary" class="form-control" placeholder="Salary" id="salary" value=${salary != null ? salary : 0}>`
}

console.log($closeBtn)
$closeBtn.forEach(btn => {
    btn.addEventListener("click", function () {
       $modalBody.innerHTML = ''
    })
})

$saveBtn.addEventListener("click", () => {
    $formRole = d.getElementById("roleForm")
    $formRole.submit()
})


