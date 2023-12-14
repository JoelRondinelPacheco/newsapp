import '../scripts/tinymce/js/tinymce/tinymce.min.js'

tinymce.init({
  selector: "#editor",
  plugins: ["advlist autolink lists link image charmap preview hr anchor pagebreak", "searchreplace wordcount visualblocks visualchars code fullscreen insertdatetime media nonbreaking table contextmenu template paste"],
  toolbar: "insertfile undo redo | styleselect | bold italic underline | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image",
});
