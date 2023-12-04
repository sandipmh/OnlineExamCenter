 var optionCount = 0;

            rmvBtn = document.getElementById("count");
            const allCheckboxElements = document.querySelectorAll("input[type='checkbox']");
            console.log(allCheckboxElements);

            allCheckboxElements.forEach(checkboxElement => {
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
                checkboxes.forEach(checkbox => {
                    checkbox.checked = isChecked; // Set the state of all other checkboxes to match the "Select All" checkbox
                });
                const count = getCheckedCount();
                console.log("Number of checked checkboxes: " + count);
                rmvBtn.textContent = count;
            });


            allCheckboxElements.forEach(checkbox => {
                checkbox.addEventListener("change", updateDivVisibility);
            });

            // Function to update div visibility and disabled state
            function updateDivVisibility() {
                const atLeastOneChecked = Array.from(checkboxes).some(checkbox => checkbox.checked);
                console.log(atLeastOneChecked)
                if (atLeastOneChecked) {
                    importBtnDiv.style.display = "block";
                    removeBtnDiv.style.display = "none";
                } else {

                    removeBtnDiv.style.display = "block";
                    importBtnDiv.style.display = "none";
                }
            }

            function getCheckedCount() {
                const checkedCheckboxes = Array.from(checkboxes).filter(checkbox => checkbox.checked);
                return checkedCheckboxes.length;
            }

            // Example of how to use the getCheckedCount function

            function setCount() {
                optionCount = 0;
            }
            // Initial call to set the initial state
            updateDivVisibility();

            function removeCurrentField(bttn) {
                const getId = bttn.id;

                const spanDiv = document.getElementById(`s${getId}`);
                const fieldDiv = document.getElementById(`f${getId}`);
                const buttnDiv = document.getElementById(`b${getId}`);

                if (spanDiv) {
                    // Remove the div element from its parent node
                    spanDiv.parentNode.removeChild(spanDiv);
                }

                if (fieldDiv) {
                    // Remove the div element from its parent node
                    fieldDiv.parentNode.removeChild(fieldDiv);
                }

                if (buttnDiv) {
                    // Remove the div element from its parent node
                    buttnDiv.parentNode.removeChild(buttnDiv);

                }
                optionCount = optionCount - 1;
            }

            function addOptions() {
                optionCount = optionCount + 1;
                console.log(`count is ${optionCount}`);
                const mainDiv = document.getElementsByTagName('div').Options;
                const spanDiv = document.createElement("div");
                const fieldDiv = document.createElement("div");
                const buttnDiv = document.createElement("div");
                spanDiv.className = "col-10 offset-1 pt-2";
                fieldDiv.className = "col-10 offset-1 pt-2 d-flex align-items-center";
                buttnDiv.className = "col-2 offset-1 pt-2";
                spanDiv.id = `s${optionCount}`;
                fieldDiv.id = `f${optionCount}`;
                buttnDiv.id = `b${optionCount}`;
                const newSpan = document.createElement("span");
                newSpan.textContent = `${optionCount} )`;
                const newField = document.createElement("input");
                newField.setAttribute('type', 'text');
                newField.className = "form-control";
                newField.id = `t${optionCount}`;
                const newButton = document.createElement("input");
                const isCorrect1 = document.createElement("input");
                const label1= document.createElement("label");
                const label2= document.createElement("label");
                label1.name="Correct";
                label2.name="In-Correct"
                isCorrect1.setAttribute('type','radio');
                isCorrect1.setAttribute('name','correct');
                isCorrect1.setAttribute('value',true);
                isCorrect1.name="wrong"
                const isCorrect2 = document.createElement("input");
                isCorrect2.setAttribute('type','radio');
                isCorrect2.setAttribute('name','correct');
                isCorrect2.setAttribute('value',false);

                //newButton.appendChild(btnIcon);
                newButton.className = "btn";
                newButton.value = " X";
                newButton.setAttribute('type', 'button');
                newButton.id = optionCount;
                newButton.onclick = function () {
                    removeCurrentField(this);
                };

                buttnDiv.appendChild(newButton);
                spanDiv.appendChild(newSpan);
                fieldDiv.appendChild(newField);
                fieldDiv.appendChild(newButton);
                fieldDiv.appendChild(isCorrect1);
                fieldDiv.appendChild(label1);
                fieldDiv.appendChild(isCorrect2);
                fieldDiv.appendChild(label2);
                mainDiv.appendChild(spanDiv);
                mainDiv.appendChild(fieldDiv);
                mainDiv.appendChild(buttnDiv);

            }

function getOptionsFromTextFields() {
    var options = [];
    for (let i = 1; i <= optionCount; i++) {
        let element = document.getElementById(`t${i}`);
        options.push(element.value);
    }
    document.getElementById("optionData").value = options.join(',');
    document.getElementById("questionForm").submit();
}

function delConfirm(button) {
                Swal.fire({
                    title: 'Are you sure?',
                    text: "You won't be able to revert this!",
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#d33',
                    cancelButtonColor: '#3085d6',
                    confirmButtonText: 'Yes, delete it!'
                }).then((result) => {
                    if (result.isConfirmed) {
                        const buttonClass = `F${button.id}`;
                        console.log(buttonClass);
                        document.querySelector(`.${buttonClass}`).submit();
                    }
                    // If the user cancels (chooses "No"), nothing will happen here.
                });
            }

   function delMultiConfirm(link) {
    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
        confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
        if (result.isConfirmed) {
            const checkboxes = document.querySelectorAll('input[class="checkbox"]:checked');
            const checkedIds = Array.from(checkboxes)
            .map(checkbox => checkbox.value);
            const form = document.getElementById('removeAll');
            const newAction = `/move/questions?checkedIds=${checkedIds.join(',')}`;
            form.action = newAction;
            document.getElementById('removeAll').submit();
        }
    });
}

            function viewItem(event) {
                const btnClass = event.id;
                console.log(`button pressed ${btnClass}`);
                //const titleContent = document.querySelector(`.title${btnClass}`).textContent;
                //const descriptionContent = document.querySelector(`.description${btnClass}`).textContent;
                const questionContent = document.getElementById(`question${btnClass}`).textContent;
                const answerContent = document.getElementById(`answer${btnClass}`).textContent;
                const marksContent = document.getElementById(`marks${btnClass}`).textContent;
                const subjectContent = document.getElementById(`subject${btnClass}`).textContent;
                const selectContent = document.getElementById(`select${btnClass}`).textContent;


                document.getElementById("qid").value = btnClass;
                document.getElementById("question").value = questionContent;
                document.getElementById("answer").value = answerContent;
                document.getElementById("marks").value = marksContent;
                document.getElementById("marks").value = marksContent;
                if(selectContent == 'Single Select') {
                   document.getElementById("singleSelect").checked = true;
                } else {
                   document.getElementById("multiSelect").checked = true;
                }
                var selectElement = document.getElementById("subjects");
                var options = selectElement.options;
                for (var i = 0; i < options.length; i++) {
                    var option = options[i];
                    var value = option.value;
                    var text = option.text;
                    if(subjectContent == option.text){
                      console.log(`got the value ${subjectContent} in ${option.text}`);
                      option.selected = true;
                      break;
                    }
                }
                getOptions(btnClass);
            }

            function getOptions(btnClass) {
                            var olElement = document.getElementById(`options${btnClass}`); // Get the <ol> element
                            var listItems = olElement.getElementsByTagName("li"); // Get all <li> elements within the <ol>
                            for (var i = 0; i < listItems.length; i++) {
                                 optionCount = i+1;
                                 var listItem = listItems[i];
                            console.log(`count is ${optionCount}`);
                            const mainDiv = document.getElementsByTagName('div').Options;
                            const spanDiv = document.createElement("div");
                            const fieldDiv = document.createElement("div");
                            const buttnDiv = document.createElement("div");
                            spanDiv.className = "col-10 offset-1 pt-2";
                            fieldDiv.className = "col-10 offset-1 pt-2 d-flex align-items-center";
                            buttnDiv.className = "col-2 offset-1 pt-2";
                            spanDiv.id = `s${optionCount}`;
                            fieldDiv.id = `f${optionCount}`;
                            buttnDiv.id = `b${optionCount}`;
                            const newSpan = document.createElement("span");
                            newSpan.textContent = `${optionCount} )`;
                            const newField = document.createElement("input");
                            newField.setAttribute('type', 'text');
                            newField.className = "form-control";
                            newField.id = `t${optionCount}`;
                            newField.value = listItem.textContent;
                            const newButton = document.createElement("input");
                            newButton.className = "btn";
                            newButton.value = " X";
                            newButton.setAttribute('type', 'button');
                            newButton.id = optionCount;
                            newButton.onclick = function () {
                                removeCurrentField(this);
                            };

                            buttnDiv.appendChild(newButton);
                            spanDiv.appendChild(newSpan);
                            fieldDiv.appendChild(newField);
                            fieldDiv.appendChild(newButton);

                            mainDiv.appendChild(spanDiv);
                            mainDiv.appendChild(fieldDiv);
                            mainDiv.appendChild(buttnDiv);
                            }

                        }


                function resetFields() {
                document.getElementById("qid").value = "";
                document.getElementById("question").value = "";
                document.getElementById("answer").value = "";
                document.getElementById("marks").value = "";
                document.getElementById("marks").value = "";
                                    }