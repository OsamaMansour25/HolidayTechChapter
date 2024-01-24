document.addEventListener("DOMContentLoaded", function () {
    const singleDatePicker = flatpickr("#singleDatePicker", {
        dateFormat: "Y-m-d",
    });

    const startDatePicker = flatpickr("#startDatePicker", {
        dateFormat: "Y-m-d",
    });

    const endDatePicker = flatpickr("#endDatePicker", {
        dateFormat: "Y-m-d",
    });

    const checkHolidayButton = document.getElementById("checkHolidayButton");
    const getNationalHolidaysButton = document.getElementById("getNationalHolidaysButton");
    const resultDiv = document.getElementById("result");

    checkHolidayButton.addEventListener("click", async () => {
        const selectedDate = singleDatePicker.input.value;
        if (selectedDate) {
            const response = await fetch(`http://localhost:8080/isHoliday/${selectedDate}`);
            const isHoliday = await response.json();
            resultDiv.textContent = isHoliday
                ? `${selectedDate} er en helligdag.`
                : `${selectedDate} er ikke en helligdag.`;
        } else {
            resultDiv.textContent = "Vælg en dato.";
        }
    });

    getNationalHolidaysButton.addEventListener("click", async () => {
        const startDate = startDatePicker.input.value;
        const endDate = endDatePicker.input.value;
        if (startDate && endDate) {
            const response = await fetch(`http://localhost:8080/nationalHolidays/${startDate}/${endDate}`);
            const nationalHolidays = await response.json();
            if (nationalHolidays.length > 0) {
                const holidayList = nationalHolidays.map((holiday) => holiday.name).join(", ");
                resultDiv.textContent = `Alle helligdage i perioden: ${holidayList}`;
            } else {
                resultDiv.textContent = "Ingen helligdage i periode.";
            }
        } else {
            resultDiv.textContent = "Vælg både start og slutdato.";
        }
    });
});
