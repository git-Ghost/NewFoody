var checkboxes = $("input[type='checkbox']"),
    submitButt = $("input[value='Register'");

checkboxes.click(function() {
    submitButt.attr("disabled", !checkboxes.is(":checked"));
});