// Foursquare API Info
const clientId = '054OFTZ0ZM2B0BRCKTVBHF0YHW1TD3ZD1X2ZSEWDGOUNNQKI';
const clientSecret = 'FT2SCHIJR0LTAEQAR4RJUXOIJUYPTWWRDRJVM0DOMXSLJ3EN';
const url = 'https://api.foursquare.com/v2/venues/explore?near=';

// OpenWeather Info
const openWeatherKey = 'd888d44dea2c916dcfa95ed200354c47';
const weatherUrl = 'https://api.openweathermap.org/data/2.5/weather';

// Page Elements
const $input = $('#city');
const $submit = $('#button');
const $destination = $('#destination');
const $container = $('.container');
const $venueDivs = [$("#venue1"), $("#venue2"), $("#venue3"), $("#venue4")];
const $weatherDiv = $("#weather1");
const weekDays = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];



const createVenueHTML = (name, location, iconSource) => {
    return `<h2>${name}</h2>
  <img class="venueimage" src="${iconSource}"/>
  <h3>Address:</h3>
  <p>${location.address}</p>
  <p>${location.city}</p>
  <p>${location.country}</p>`;
}

const createWeatherHTML = (currentDay) => {
    console.log(currentDay)
    return `<h2>${weekDays[(new Date()).getDay()]}</h2>
		<h2>Temperature: ${kelvinToFahrenheit(currentDay.main.temp)}&deg;F</h2>
		<h2>Condition: ${currentDay.weather[0].description}</h2>
  	<img src="https://openweathermap.org/img/wn/${currentDay.weather[0].icon}@2x.png">`;
}

const kelvinToFahrenheit = k => ((k - 273.15) * 9 / 5 + 32).toFixed(0);

//AJAX functions
const getVenues = async() => {
    const city = $input.val();
    const urlToFetch = `${url}${city}&limit=10&client_id=${clientId}&client_secret=${clientSecret}&v=20180101`;
    try {
        const response = await fetch(urlToFetch);
        if (response.ok) {
            const jsonResponse = await response.json();
            const venues = jsonResponse.response.groups[0].items.map(item => item.venue);
            console.log(venues);
            return venues;
        }
    } catch(error){
        console.log(error);
    }
}

const getForecast = async() => {
    const urlToFetch = (`${weatherUrl}?&q=${$input.val()}&APPID=${openWeatherKey} `)
    try{
        const response = await fetch(urlToFetch);
        if(response.ok){
            const jsonResponse = await response.json();
            console.log(jsonResponse);
            return jsonResponse;
        }
    } catch(error){
        console.log(error);
    }
}




// Render functions
const renderVenues = (venues) => {
    $venueDivs.forEach(($venue, index) => {
        // Add your code here:
        const venue = venues[index];
        const venueIcon = venue.categories[0].icon;
        const venueImgSrc = `${venueIcon.prefix}bg_64${venueIcon.suffix}`;
        let venueContent = createVenueHTML(venue.name, venue.location, venueImgSrc);
        $venue.append(venueContent);
    });
    $destination.append(`<h2>${venues[0].location.city}</h2>`);
}

const renderForecast = (day) => {

    let weatherContent = createWeatherHTML(day);
    $weatherDiv.append(weatherContent);
}




//search function
const executeSearch = () => {
    $venueDivs.forEach(venue => venue.empty());
    $weatherDiv.empty();
    $destination.empty();
    $container.css("visibility", "visible");
    getVenues()
        .then(venues=>renderVenues(venues));
    getForecast().then(sabrina => renderForecast(sabrina))
    return false;
}

$submit.click(executeSearch);