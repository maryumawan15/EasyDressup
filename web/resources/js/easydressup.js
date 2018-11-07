function toogleMenu(event) {
    var i;

    var subCats = document.getElementById("subCat_" + event.target.id);
    if (subCats.classList.contains('show')) {
        subCats.classList.remove('show');
    } else {
        subCats.classList.add('show');
    }
    //document.getElementById("myDropdown").classList.toggle("show");
}
function showModal(clothId) {
    var modal = document.getElementById('modal');
    document.getElementById("update_clothId").value=clothId;
    modal.style.display = "block";
}

function closeModal() {
    var modal = document.getElementById('modal');
    modal.style.display = "none";
}