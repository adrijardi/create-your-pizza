<jsp:useBean id="pizzaBases" scope="request" type="java.util.List<com.pizza.domain.PizzaBase>"/>
<jsp:useBean id="toppings" scope="request" type="java.util.List<com.pizza.domain.Topping>"/>
<jsp:useBean id="pizza" scope="request" type="com.pizza.domain.Pizza"/>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<%@ taglib uri="/WEB-INF/util.tld" prefix="util" %>
<tags:main title="Create your pizza!">

    <div class="row">
        <section class="medium-10 medium-centered columns" ng-controller="ToppingsCtrl">
            <h1 class="subheader text-center">Don't let us tell you how a pizza is made</h1>
            <h2 class="text-center">Create your own!</h2>

            <fieldset>
                <legend>Pizza base</legend>
                <c:if test="${not empty pizza.pizzaBase.id}">
                    <c:set var="init" value="ng-init='pizzaBase=${pizza.pizzaBase.id}'"/>
                </c:if>
                <select id="pizza-base-select" name="pizza-base" ng-model="pizzaBase" ng-change="base()" ${init}>
                    <c:forEach items="${pizzaBases}" var="base">
                        <option value="${base.id}" ${pizza.pizzaBase.id == base.id?'selected':''}>${base.name}</option>
                    </c:forEach>
                </select>
            </fieldset>

            <fieldset ng-show="pizzaBase">
                <legend>Toppings</legend>
                <div class="row">
                    <c:forEach items="${toppings}" var="topping" varStatus="status">
                        <div class="small-6 medium-2 columns ${status.last?'end':''}">
                            <span class="topping-name">${topping.name}</span>
                            <div class="switch">
                                <input id="toppingSwitch${topping.id}" type="checkbox" name="topping" ng-model="pizzaToppings[${topping.id}]" ng-change="topping(${topping.id})" ng-init="pizzaToppings[${topping.id}]=${util:contains(pizza.toppings, topping)}" >
                                <label id="toppingLabel${topping.id}" for="toppingSwitch${topping.id}"></label>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </fieldset>

            <div id="priceRow" class="row" ng-show="price">
                <div class="medium-6 medium-centered columns">
                    <ul class="pricing-table">
                        <li class="title">Create your own pizza</li>
                        <li id="totalPrice" class="price">{{price}}</li>
                        <li class="cta-button">
                            <form id="buyNow" action="<c:url value='/order/buy'/>" method="post">
                                <button type="submit">Buy Now</button>
                            </form>
                        </li>
                    </ul>
                </div>
            </div>
        </section>
    </div>

</tags:main>