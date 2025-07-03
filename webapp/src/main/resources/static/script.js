document.addEventListener('DOMContentLoaded', () => {
    const statusBtn = document.querySelector(".PageMain-centralBox-reports-filters-btn");
    const filtersList = document.querySelector(".PageMain-centralBox-reports-filters-list");
    const actives = document.querySelector(".PageMain-centralBox-reports-filters-active");
    const checkboxes = document.querySelectorAll(".PageMain-centralBox-reports-filters-list input[type='checkbox']");
    const cards = document.querySelectorAll(".ReportCard");
    console.log("cards", cards);
    console.log("filters", checkboxes);

    const checkedFilters = [];

    if (statusBtn && filtersList) {
        statusBtn.addEventListener("click", function () {
            filtersList.classList.toggle("active");
        });
    }

    const showCards = (event) => {
        const filterStatus = event.target.checked;
        const filterValue = event.target.getAttribute("data-filter-status");
        console.log(filterValue, filterStatus);
        if (filterStatus) {
            checkedFilters.push(filterValue);
        } else {
            const checkedFiltersIndex = checkedFilters.findIndex((filter) => filter === filterValue);
            checkedFilters.splice(checkedFiltersIndex, 1);
        }

    };

    checkboxes.forEach(checkbox => {
        checkbox.addEventListener("change", showCards);
    });

    const toggleButton = document.querySelector('.toggle-location');
    if (toggleButton) {
        toggleButton.addEventListener('click', () => {
            toggleButton.classList.toggle('active');
        });
    }

    const element = document.getElementById('categoryIds');

    if (element) {
        const choices = new Choices(element, {
            removeItemButton: true,
            searchEnabled: true,
            itemSelectText: '',
            shouldSort: false,
            placeholder: true,
            placeholderValue: 'Select categories',
            classNames: {
                containerOuter: 'choices',
            }
        });
    }
});
