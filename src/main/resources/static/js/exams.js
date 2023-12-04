rmvBtn = document.getElementById("count");
const allCheckboxElements = document.querySelectorAll("input[type='checkbox']");
console.log(allCheckboxElements);

allCheckboxElements.forEach((checkboxElement) => {
  checkboxElement.addEventListener("change", function () {
    // 'this' within this function refers to the checkbox that triggered the change event
    console.log("Checkbox value: " + this.value);

    // You can also check if the checkbox is checked
    if (this.checked) {
      console.log("Checkbox is checked");
    } else {
      console.log("Checkbox is not checked");
    }

    const count = getCheckedCount();
    console.log("Number of checked checkboxes: " + count);
    rmvBtn.textContent = count;
  });
});

const removeBtnDiv = document.querySelector(".extrapanel1");
const importBtnDiv = document.querySelector(".extrapanel2");

// Get references to the "Select All" checkbox and all the other checkboxes
const selectAllCheckbox = document.getElementById("selectAll");
const checkboxes = document.querySelectorAll(".checkbox");

// Add an event listener to the "Select All" checkbox
selectAllCheckbox.addEventListener("change", function () {
  const isChecked = this.checked; // Get the checked state of the "Select All" checkbox
  checkboxes.forEach((checkbox) => {
    checkbox.checked = isChecked; // Set the state of all other checkboxes to match the "Select All" checkbox
  });
  const count = getCheckedCount();
  console.log("Number of checked checkboxes: " + count);
  rmvBtn.textContent = count;
});

allCheckboxElements.forEach((checkbox) => {
  checkbox.addEventListener("change", updateDivVisibility);
});

// Function to update div visibility and disabled state
function updateDivVisibility() {
  const atLeastOneChecked = Array.from(checkboxes).some(
    (checkbox) => checkbox.checked
  );
  console.log(atLeastOneChecked);
  if (atLeastOneChecked) {
    importBtnDiv.style.display = "block";
    removeBtnDiv.style.display = "none";
  } else {
    removeBtnDiv.style.display = "block";
    importBtnDiv.style.display = "none";
  }
}

function getCheckedCount() {
  const checkedCheckboxes = Array.from(checkboxes).filter(
    (checkbox) => checkbox.checked
  );
  return checkedCheckboxes.length;
}

updateDivVisibility();

function makeActive(currentNav) {
  const allNavLinks = document.querySelectorAll(".nav-link"); // Add a dot (.) before "nav-link" to select elements with the class.
  allNavLinks.forEach((selectedNav) => {
    selectedNav.style.backgroundColor = "";
  });
  currentNav.style.backgroundColor = "green";
  console.log("color added");
}

function loadQuestions(selectedValue) {
  fetch(`http://localhost:8080/get/questions?subjectIds=${selectedValue}`)
    .then((response) => response.json())
    .then((data) => {
      const selectElement = document.getElementById("questionSelect");
      selectElement.innerHTML =
        '<option value="" disabled selected>Select a question</option>';
      data.forEach((question) => {
        const option = document.createElement("option");
        option.value = question.marks; // Set the value to the question's ID or a unique identifier
        option.id = question.qid;
        option.textContent = question.question; // Set the text to the question's text
        selectElement.appendChild(option);
      });
    })
    .catch((error) => {
      console.error("Error:", error);
    });
}
document.getElementById("selectOption").addEventListener("change", function () {
  var selectedValue = this.value;
  document.getElementById("marks").value = "";
  loadQuestions(selectedValue);
});

const selectElement = document.getElementById("questionSelect");
selectElement.addEventListener("change", function () {
  const selectedOptions = Array.from(this.selectedOptions);
  const arraySize = selectedOptions.length;
  const selectedIds = selectedOptions.map((option) =>
    parseInt(option.value, 10)
  );
  const sum = selectedIds.reduce(
    (accumulator, currentValue) => accumulator + currentValue,
    0
  );
  document.getElementById("marks").value = sum;
  document.getElementById("numberOfQuestions").value = arraySize;
  const optionsIds = selectedOptions.map((option) => option.getAttribute("id"));
  // Display the selected option IDs
  const ids = optionsIds.join(", ");
  document.getElementById("questionData").value = ids;
  console.log(ids);
});

function delConfirm(button) {
  Swal.fire({
    title: "Are you sure?",
    text: "You won't be able to revert this!",
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#d33",
    cancelButtonColor: "#3085d6",
    confirmButtonText: "Yes, delete it!",
  }).then((result) => {
    if (result.isConfirmed) {
      // User confirmed, submit the form for deletion

      const buttonClass = `F${button.id}`;
      console.log(buttonClass);
      document.querySelector(`.${buttonClass}`).submit();
    }
    // If the user cancels (chooses "No"), nothing will happen here.
  });
}

function delMultiConfirm(link) {
  Swal.fire({
    title: "Are you sure?",
    text: "You won't be able to revert this!",
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#d33",
    cancelButtonColor: "#3085d6",
    confirmButtonText: "Yes, delete it!",
  }).then((result) => {
    if (result.isConfirmed) {
      // Get the checkboxes by their name or any other identifier
      const checkboxes = document.querySelectorAll(
        'input[class="checkbox"]:checked'
      );

      const checkedIds = Array.from(checkboxes).map(
        (checkbox) => checkbox.value
      );

      const form = document.getElementById("removeAll");
      const newAction = `/move/exams?eids=${checkedIds.join(",")}`;
      form.action = newAction;
      document.getElementById("removeAll").submit();
    }
    // If the user cancels (chooses "No"), nothing will happen here.
  });
}

function resetFields() {
  document.getElementById("eid").value = "";
  document.getElementById("examTitle").value = "";
  document.getElementById("description").value = "";
  document.getElementById("startDate").value = "";
  document.getElementById("lastDate").value = "";
  document.getElementById("marks").value = "";
  document.getElementById("time").value = "";
  document.getElementById("numberOfQuestions").value = "";
}

function viewItem(event) {
  const btnClass = event.id;
  console.log(`button pressed ${btnClass}`);
  const titleContent = document.getElementById(`examTitle${btnClass}`).textContent;
  const descriptionContent = document.getElementById(`description${btnClass}`).textContent;
  const marksContent = document.getElementById(`marks${btnClass}`).textContent;
  const startDateContent = document.getElementById(`startDate${btnClass}`).textContent;
  const lastDateContent = document.getElementById(`lastDate${btnClass}`).textContent;
  const timeContent = document.getElementById(`time${btnClass}`).textContent;
  const questionsContent = document.getElementById(`totalQuestion${btnClass}`).textContent;
  const subjectContent = document.getElementById(`subject${btnClass}`).textContent;
 // const subjectIdContent = document.getElementById(`subjectId${btnClass}`).textContent;

  const selectElement  = document.getElementById("selectOption");
  var option = document.createElement("option");
  option.text = subjectContent;
  option.value = subjectContent; // You can set the value to any meaningful value if needed
  selectElement.appendChild(option);

  document.getElementById("eid").value = btnClass;
  document.getElementById("examTitle").value = titleContent;
  document.getElementById("description").value = descriptionContent;
  document.getElementById("startDate").value = startDateContent;
  document.getElementById("lastDate").value = lastDateContent;
  document.getElementById("marks").value = marksContent;
  document.getElementById("time").value = timeContent;
  document.getElementById("numberOfQuestions").value = questionsContent;
}
