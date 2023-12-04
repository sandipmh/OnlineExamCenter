        const update = document.getElementById('updatebtn');
        const cancel = document.getElementById('cancelbtn');
        update.style.display = 'none';
        cancel.style.display = 'none';

        var link = document.getElementById("myLink");
        link.addEventListener("click", function (event) {
            event.preventDefault();
            enableField();
            // alert("You clicked on the link!");
        });

        function enableField() {
            // Get a reference to the input element
            var input = document.getElementById("name");
            input.disabled = false;
            var input = document.getElementById("password");
            input.disabled = false;
            var input = document.getElementById("email");
            input.disabled = false;
            var input = document.getElementById("contact");
            input.disabled = false;
            update.style.display = 'inline-block';
            cancel.style.display = 'inline-block';


        }
        function updateDetails() {
            // swal("Profile Updated!");
            Swal.fire({
                // position: 'top-end',
                icon: 'success',
                title: 'Your profile has been saved',
                // showConfirmButton: true,
                timer: 2000
            })
        }