
const statusBtn = document.querySelector(".PageMain-centralBox-reports-filters-btn");
const filtersList = document.querySelector(".PageMain-centralBox-reports-filters-list");
const actives = document.querySelector(".PageMain-centralBox-reports-filters-active")
const checkboxes = document.querySelectorAll(".PageMain-centralBox-reports-filters-list input[type='checkbox']");
const cards = document.querySelectorAll(".ReportCard");
console.log("cards", cards);
console.log("filters", checkboxes);
//Show or hide filters
const checkedFilters = [];
//Gestion du filter faire apparaitre
statusBtn.addEventListener("click", function () {
    filtersList.classList.toggle("active");
});
const showCards = (event) => {
    const filterStatus = event.target.checked;
    const filterValue = event.target.getAttribute("data-filter-status");
    console.log(event.target.getAttribute("data-filter-status"));
    console.log(event.target.checked);
    if (filterStatus) {
        checkedFilters.push(filterValue)
    }
    if (!filterStatus) {
        const checkedFiltersIndex = checkedFilters.findIndex((filter) => filter === filterValue);
        checkedFilters.splice(checkedFiltersIndex, 1)
        console.log("checkedFilters", checkedFilters)
    }
    /* const attribute = document */

}
checkboxes.forEach(checkbox => {
    checkbox.addEventListener("change", showCards);


})



