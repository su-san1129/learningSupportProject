function responsibleCompanies() {
  radio = document.getElementsByName("responsibleCompany");
  if (radio[0].checked) {
    document.getElementById("responsibleCompanyBox").style.display = "none";
  } else if (radio[1].checked) {
    document.getElementById("responsibleCompanyBox").style.display = "";
  }
  // オンロードさせ、リロード時に選択を保持
  window.onload = responsibleCompanies;
}

function addCompany() {
  var selectIndex = document.adminEditForm.companySelect.selectedIndex;
  var companyName = document.adminEditForm.companySelect.options[selectIndex].text;
  var companyId = document.adminEditForm.companySelect.value;
  var companyIdList = document.getElementsByName("companyIdList");
  var flag = false;
  companyIdList.forEach(id => {
    if (id.value === companyId) {
      alert("企業が重複しているため、選択できません。");
      flag = true;
    }
    if (companyId == 0) {
      flag = true;
    }
  });
  if (flag) return;
  var div_element = document.createElement("div");
  div_element.innerHTML =
    '<span><button type="button" onclick="deleteCompany(this);">削除</button>&nbsp;' +
    companyName +
    '<input type="hidden" name="companyIdList" value="' +
    companyId +
    '" /></span>';
  var parent_object = document.getElementById("companyList");
  parent_object.appendChild(div_element);
}

function deleteCompany(deleteButton) {
  var parent_object = deleteButton.parentNode;
  alert("選択された企業を削除しますか？");
  deleteButton.parentNode.parentNode.removeChild(deleteButton.parentNode);
}
