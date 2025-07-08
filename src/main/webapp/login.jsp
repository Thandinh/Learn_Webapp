<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng nhập</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="assets/css/style.css">
    <link rel="stylesheet" href="assets/css/grid.css">
    <link rel="stylesheet" href="assets/css/form.css">
</head>

<body>
    <%@include file="./inc/header.jsp" %>

    <div id="main" class="register">
        <div class="container">
            <div class="navig">
                <a href="trang-chu">Trang chủ</a>
                <i class="fa-solid fa-chevron-right"></i>
                <a class="item active" href="">Đăng nhập tài khoản</a>
            </div>

            <form action="dang-nhap" id="register-form" class="form" method="post">
                <h2 class="title">ĐĂNG NHẬP TÀI KHOẢN</h2>
                <div class="regis-social">
                    <a href=""><img src="assets/images/fb-btn.svg" alt=""></a>
                    <a href=""><img src="assets/images/gp-btn.svg" alt=""></a>
                </div>

                <div class="form-group">
                    <input name="email" type="text" rules="required|email" placeholder="Email">
                    <span class="form-message"></span>
                </div>


                <div class="form-group">
                    <input name="password" type="password" rules="required|min:3" placeholder="Mật khẩu">
                    <span class="form-message"></span>
                </div>

                <button class="form-submit">Đăng nhập</button>
            </form>
        </div>
    </div>

    <div id="spacing-footer"></div>

    <%@include file="./inc/footer.jsp" %>



    <script>
         <c:if test="${error != null}">
                    alert("${error}")
         </c:if>


        let productsCart = JSON.parse(localStorage.getItem('productscart')) || [];
        document.querySelector('.cart-count').innerText = productsCart.length;

        let form = new Validator('#register-form');




        function Validator(formSelector) {
            const _this = this;
            let formRules = {};

            function getParent(element, selector) {
                while (element.parentElement) {
                    if (element.parentElement.matches(selector)) {
                        return element.parentElement;
                    }
                    element = element.parentElement;
                }
            }

            const validatorRules = {
                required: function (value) {
                    return value ? undefined : 'Vui lòng nhập trường này';
                },
                email: function (value) {
                    const regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
                    return regex.test(value) ? undefined : 'Vui lòng nhập email';
                },
                min: function (min) {
                    return function (value) {
                        return value.length >= min ? undefined : `Vui lòng nhập ít nhất ${min} kí tự`;
                    }
                },
                confirm: function (selectorPassword) {
                    return function (value) {
                        let confirmValue = document.querySelector(selectorPassword).value;
                        return value === confirmValue ? undefined : 'Mật khẩu nhập lại không khớp'
                    }
                }
            };

            const formElement = document.querySelector(formSelector);

            if (formElement) {
                let inputs = formElement.querySelectorAll('[name][rules]');


                for (let input of inputs) {
                    let rules = input.getAttribute('rules').split('|')

                    for (let rule of rules) {

                        let isRuleHasValue = rule.includes(':');

                        let ruleInfo;
                        if (isRuleHasValue) {
                            ruleInfo = rule.split(':');

                            rule = ruleInfo[0];
                        }

                        let ruleFunc = validatorRules[rule];

                        if (isRuleHasValue) {
                            ruleFunc = validatorRules[rule](ruleInfo[1]);
                        }


                        if (Array.isArray(formRules[input.name])) {
                            formRules[input.name].push(ruleFunc);
                        } else {
                            formRules[input.name] = [ruleFunc];
                        }
                    }

                    input.onblur = handleValidate;
                    input.oninput = handleClearError;
                }

                function handleValidate(event) {

                    let rules = formRules[event.target.name];

                    let errorMessage;
                    for (let i = 0; i < rules.length; i++) {
                        errorMessage = rules[i](event.target.value);
                        if (errorMessage) break;
                    };

                    if (errorMessage) {
                        let formGroup = getParent(event.target, '.form-group');

                        if (formGroup) {
                            let formMessage = formGroup.querySelector('.form-message');
                            if (formMessage) {
                                formMessage.innerText = errorMessage;
                                formGroup.classList.add('invalid');
                            }
                        }
                    }
                    return errorMessage;
                }

                function handleClearError(event) {
                    let formGroup = getParent(event.target, '.form-group');
                    if (formGroup.classList.contains('invalid')) {
                        formGroup.classList.remove('invalid');
                        let formMessage = formGroup.querySelector('.form-message');
                        if (formMessage) {
                            formMessage.innerText = '';
                        }
                    }
                }


                formElement.onsubmit = function (event) {
                    event.preventDefault();

                    const inputs = formElement.querySelectorAll('[name][rules]');
                    let isValid = true;
                    for (let input of inputs) {
                        if (handleValidate({ target: input })) {
                            isValid = false;
                        }
                    }

                    if (isValid) {
                        if (typeof _this.onSubmit === 'function') {
                            const enableInputs = formElement.querySelectorAll('[name][rules]:not([disabled])');
                            const formValues = Array.from(enableInputs).reduce(function (values, input) {
                                switch (input.type) {
                                    case 'radio':
                                        values[input.name] = formElement.querySelector('input[name="' + input.name + '"]:checked').value;
                                        break;
                                    case 'checkbox':
                                        if (Array.isArray(values[input.name])) {
                                            values[input.name] = [];
                                        }
                                        if (input.matches(':checked')) {
                                            values[input.name].push(input.value);
                                        }
                                        break;
                                    case 'file':
                                        values[input.name] = input.files;
                                        break;
                                    default:
                                        values[input.name] = input.value;
                                }
                                return values;
                            }, {})


                            formElement.submit();
                        } else {
                            formElement.submit()
                        }
                    }
                }


            }
        }
    </script>
</body>

</html>