
document.addEventListener('DOMContentLoaded', () => {
    const statusBtn = document.querySelector(".PageMain-centralBox-reports-filters-btn");
    const filtersList = document.querySelector(".PageMain-centralBox-reports-filters-list");
    const actives = document.querySelector(".PageMain-centralBox-reports-filters-active");
    const checkboxes = document.querySelectorAll(".PageMain-centralBox-reports-filters-list input[type='checkbox']");
    const cards = document.querySelectorAll(".ReportCard");
    console.log("cards", cards);
    console.log("filters", checkboxes);


    //To fix sideBar in desktop device
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



//To manage incidents filters in dashboard
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



    // Toggle location + geolocation + Google Maps Geocoding


    const toggleButton = document.querySelector('.toggle-location');
    const addressInput = document.querySelector('#address');
    const postalCodeInput = document.querySelector('#postalCode');
    const cityInput = document.querySelector('#city');
    const latitudeInput = document.querySelector('#latitude');
    const longitudeInput = document.querySelector('#longitude')

    toggleButton.addEventListener('click', () => {
        toggleButton.classList.toggle('active');

        if (toggleButton.classList.contains('active')) {
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(async (position) => {
                    const lat = position.coords.latitude;
                    const lng = position.coords.longitude;
                    latitudeInput.value = lat;
                    longitudeInput.value = lng;


                    try {
                        const response = await fetch(`https://maps.googleapis.com/maps/api/geocode/json?latlng=${lat},${lng}&key=${GOOGLE_API_KEY}`);

                        if (!response.ok) {
                            throw new Error(`HTTP error! status: ${response.status}`);
                        }

                        const data = await response.json();
                        console.log(data);

                        if (data.status === 'OK' && data.results.length > 0) {
                            const result = data.results[0];
                            addressInput.value = result.address_components[0].long_name + " " + result.address_components[1].long_name;

                            function getComponent(types) {
                                return result.address_components.find(component =>
                                    types.every(type => component.types.includes(type))
                                );
                            }

                            const postalCodeComponent = getComponent(['postal_code']);
                            postalCodeInput.value = postalCodeComponent ? postalCodeComponent.long_name : '';

                            const cityComponent = getComponent(['locality']);
                            if (cityComponent) {
                                const cityName = cityComponent.long_name;

                                const options = cityInput.options;
                                let cityId = '';
                                for (let i = 0; i < options.length; i++) {
                                    if (options[i].text === cityName) {
                                        cityId = options[i].value;
                                        break;
                                    }
                                }
                                cityInput.value = cityId;
                            } else {
                                cityInput.value = '';
                            }

                        } else {
                            addressInput.value = 'Adresse non trouvée';
                            postalCodeInput.value = '';
                            cityInput.value = '';
                        }
                    } catch (error) {
                        addressInput.value = "Erreur lors de l'obtention de l'adresse";
                        postalCodeInput.value = '';
                        cityInput.value = '';
                        console.error(error);
                    }
                }, (error) => {
                    addressInput.value = "Erreur lors de l'obtention de la localisation";
                    postalCodeInput.value = '';
                    cityInput.value = '';
                    console.error(error);
                });
            } else {
                addressInput.value = 'Géolocalisation non prise en charge';
                postalCodeInput.value = '';
                cityInput.value = '';
            }
        } else {
            // Clear fields when toggle off
            addressInput.value = '';
            postalCodeInput.value = '';
            cityInput.value = '';
        }
    });


    //To manage multiple choices in category fields
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

//To manage image in updateIncident modal


function removeImageFromBack() {
  document.getElementById('imageFromBack').style.display="none";
  document.getElementById('removeImage').style.display="none"
}

function showEditModal() {

    document.getElementById('editModal').style.display = 'block';
    // document.getElementById('PageMain-centralBoxDetail').style.visibility = 'hidden';
}

function hideEditModal() {
    document.getElementById('editModal').style.display = 'none';
    // document.getElementById('PageMain-centralBoxDetail').style.display = 'visible'
}