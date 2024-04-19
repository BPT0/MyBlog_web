document.addEventListener('DOMContentLoaded', function () {
  var dropdownMenuList = document.querySelectorAll('.dropdown-toggle');
  dropdownMenuList.forEach(function (dropdownToggle) {
      dropdownToggle.addEventListener('click', function (event) {
          event.preventDefault();
          var dropdownMenu = this.nextElementSibling;
          if (dropdownMenu.style.display === 'block') {
              dropdownMenu.style.display = 'none';
          } else {
              dropdownMenu.style.display = 'block';
          }
      });
  });
});