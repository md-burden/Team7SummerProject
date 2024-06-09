const html = '<div><div class="input-group mt-1" id="ingredients"><span class="input-group-text">Name: </span><input type="text" class="form-control" placeholder="Ingredient Name *" aria-label="Recipient\'s username" aria-describedby="button-addon2"> <span class="input-group-text">Quantity:</span> <input type="text" class="form-control" placeholder="Quantity *" aria-label="Recipient\'s username" aria-describedby="button-addon2"> <button class="btn btn-outline-secondary" type="button" id="delete_field"> <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="rgba(255,0,0,0.75)" class="bi bi-trash" viewBox="0 0 16 16"> <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0z"/> <path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4zM2.5 3h11V2h-11z"/> </svg> </button> </div>';

let addBtn = document.getElementById("add_field");
let container = document.getElementById("field-container");

addBtn.addEventListener("click", function (event) {
    console.log("Button Add")
    const template = document.createElement("template");
    template.innerHTML = html;
    const nodes = template.content.children;
    container.appendChild(...nodes);
    if (container.hasChildNodes()) {
        let dltBtn = document.getElementById("delete_field")
        dltBtn.addEventListener("click", function (e) {
            console.log("Button Delete")
            let elements = container.children;
            container.removeChild(elements[0])
            e.stopPropagation();
        });
    }
});