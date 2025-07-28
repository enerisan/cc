
document.addEventListener('DOMContentLoaded', () => {
    const statusBtn = document.querySelector(".PageMain-centralBox-reports-filters-btn");
    const filtersList = document.querySelector(".PageMain-centralBox-reports-filters-list");
    const actives = document.querySelector(".PageMain-centralBox-reports-filters-active");
    const checkboxes = document.querySelectorAll(".PageMain-centralBox-reports-filters-list input[type='checkbox']");
    const cards = document.querySelectorAll(".ReportCard");
    console.log("cards", cards);
    console.log("filters", checkboxes);

    function alignFixedSidebar() {
        const wrapper = document.querySelector('.WrapperBoss');
        const bar = document.querySelector('.PageMain-bar');

        if (!wrapper || !bar) return;

        const wrapperRect = wrapper.getBoundingClientRect();
        const offsetRight = window.innerWidth - (wrapperRect.left + wrapperRect.width);


        bar.style.right = `${Math.max(offsetRight, 0)}px`;
    }

    window.addEventListener('load', alignFixedSidebar);
    window.addEventListener('resize', alignFixedSidebar);




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

    //Toggle location
    const toggleButton = document.querySelector('.toggle-location');
    if (toggleButton) {
        toggleButton.addEventListener('click', () => {
            toggleButton.classList.toggle('active');
        });
    }

    //To mangage multiple choices in category fields
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

 //To show image name added in addIncident template
    document.getElementById('image').addEventListener('change', function() {
        const fileName = this.files.length > 0 ? this.files[0].name : '';
        document.getElementById('fileName').textContent = fileName;
    });
});



function showEditModal() {

    document.getElementById('editModal').style.display = 'block';
    // document.getElementById('PageMain-centralBoxDetail').style.visibility = 'hidden';
}

function hideEditModal() {
    document.getElementById('editModal').style.display = 'none';
    // document.getElementById('PageMain-centralBoxDetail').style.display = 'visible'
}