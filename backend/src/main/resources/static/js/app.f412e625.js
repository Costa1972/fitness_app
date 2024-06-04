(function(){"use strict";var e={7802:function(e,r,s){var t=s(3751),n=s(641),a=s(33);const o={id:"app"},i={class:"navbar navbar-expand navbar-dark bg-dark"},u=(0,n.Lk)("a",{href:"/",class:"navbar-brand"},"FITNESS",-1),l={class:"navbar-nav mr-auto"},c={class:"nav-item"},d={key:0,class:"nav-item"},m={class:"nav-item"},g={key:0,class:"navbar-nav ml-auto"},f={class:"nav-item"},p={class:"nav-item"},h={key:1,class:"navbar-nav ml-auto"},v={class:"nav-item"},b={class:"nav-item"},k={class:"container"};function L(e,r,s,L,w,y){const F=(0,n.g2)("font-awesome-icon"),E=(0,n.g2)("router-link"),S=(0,n.g2)("router-view");return(0,n.uX)(),(0,n.CE)("div",o,[(0,n.Lk)("nav",i,[u,(0,n.Lk)("div",l,[(0,n.Lk)("li",c,[(0,n.bF)(E,{to:"/home",class:"nav-link"},{default:(0,n.k6)((()=>[(0,n.bF)(F,{icon:"home"}),(0,n.eW)(" Home ")])),_:1})]),y.showAdminBoard?((0,n.uX)(),(0,n.CE)("li",d,[(0,n.bF)(E,{to:"/admin",class:"nav-link"},{default:(0,n.k6)((()=>[(0,n.eW)("Admin Board")])),_:1})])):(0,n.Q3)("",!0),(0,n.Lk)("li",m,[y.currentUser?((0,n.uX)(),(0,n.Wv)(E,{key:0,to:"/user",class:"nav-link"},{default:(0,n.k6)((()=>[(0,n.eW)("User")])),_:1})):(0,n.Q3)("",!0)])]),y.currentUser?(0,n.Q3)("",!0):((0,n.uX)(),(0,n.CE)("div",g,[(0,n.Lk)("li",f,[(0,n.bF)(E,{to:"/register",class:"nav-link"},{default:(0,n.k6)((()=>[(0,n.bF)(F,{icon:"user-plus"}),(0,n.eW)(" Sign Up ")])),_:1})]),(0,n.Lk)("li",p,[(0,n.bF)(E,{to:"/login",class:"nav-link"},{default:(0,n.k6)((()=>[(0,n.bF)(F,{icon:"sign-in-alt"}),(0,n.eW)(" Login ")])),_:1})])])),y.currentUser?((0,n.uX)(),(0,n.CE)("div",h,[(0,n.Lk)("li",v,[(0,n.bF)(E,{to:"/profile",class:"nav-link"},{default:(0,n.k6)((()=>[(0,n.bF)(F,{icon:"user"}),(0,n.eW)(" "+(0,a.v_)(y.currentUser.username),1)])),_:1})]),(0,n.Lk)("li",b,[(0,n.Lk)("a",{class:"nav-link",onClick:r[0]||(r[0]=(0,t.D$)(((...e)=>y.logOut&&y.logOut(...e)),["prevent"]))},[(0,n.bF)(F,{icon:"sign-out-alt"}),(0,n.eW)(" LogOut ")])])])):(0,n.Q3)("",!0)]),(0,n.Lk)("div",k,[(0,n.bF)(S)])])}s(4114);var w={computed:{currentUser(){return this.$store.state.auth.user},showAdminBoard(){return!(!this.currentUser||!this.currentUser["roles"])&&this.currentUser["roles"].includes("ROLE_ADMIN")},showModeratorBoard(){return!(!this.currentUser||!this.currentUser["roles"])&&this.currentUser["roles"].includes("ROLE_MODERATOR")}},methods:{logOut(){this.$store.dispatch("auth/logout"),this.$router.push("/login")}}},y=s(6262);const F=(0,y.A)(w,[["render",L]]);var E=F,S=s(5220);const O={class:"container"},A={class:"jumbotron"};function j(e,r,s,t,o,i){return(0,n.uX)(),(0,n.CE)("div",O,[(0,n.Lk)("header",A,[(0,n.Lk)("h3",null,(0,a.v_)(o.content),1)])])}var I=s(619),C={name:"Home",data(){return{content:""}},mounted(){I.A.getPublicContent().then((e=>{this.content=e.data}),(e=>{this.content=e.response&&e.response.data&&e.response.data.message||e.message||e.toString()}))}};const _=(0,y.A)(C,[["render",j]]);var U=_;const x={class:"row justify-content-center"},P={class:"col-md-6"},M={class:"card card-container"},T=(0,n.Lk)("img",{id:"profile-img",src:"//ssl.gstatic.com/accounts/ui/avatar_2x.png",class:"profile-img-card"},null,-1),X={class:"form-group"},$=(0,n.Lk)("label",{for:"username"},"Username",-1),q={class:"form-group"},W=(0,n.Lk)("label",{for:"password"},"Password",-1),B={class:"form-group"},N=["disabled"],R={class:"spinner-border spinner-border-sm"},Q=(0,n.Lk)("span",null,"Login",-1),D={class:"form-group"},Y={key:0,class:"alert alert-danger",role:"alert"};function G(e,r,s,o,i,u){const l=(0,n.g2)("Field"),c=(0,n.g2)("ErrorMessage"),d=(0,n.g2)("Form");return(0,n.uX)(),(0,n.CE)("div",x,[(0,n.Lk)("div",P,[(0,n.Lk)("div",M,[T,(0,n.bF)(d,{onSubmit:u.handleLogin,"validation-schema":i.schema},{default:(0,n.k6)((()=>[(0,n.Lk)("div",X,[$,(0,n.bF)(l,{name:"username",type:"text",class:"form-control"}),(0,n.bF)(c,{name:"username",class:"error-feedback"})]),(0,n.Lk)("div",q,[W,(0,n.bF)(l,{name:"password",type:"password",class:"form-control"}),(0,n.bF)(c,{name:"password",class:"error-feedback"})]),(0,n.Lk)("div",B,[(0,n.Lk)("button",{class:"btn btn-primary btn-block",disabled:i.loading},[(0,n.bo)((0,n.Lk)("span",R,null,512),[[t.aG,i.loading]]),Q],8,N)]),(0,n.Lk)("div",D,[i.message?((0,n.uX)(),(0,n.CE)("div",Y,(0,a.v_)(i.message),1)):(0,n.Q3)("",!0)])])),_:1},8,["onSubmit","validation-schema"])])])])}var J=s(5261),H=s(2664),K={name:"Login",components:{Form:J.lV,Field:J.D0,ErrorMessage:J.Kw},data(){const e=H.Ik().shape({username:H.Yj().required("Username is required!"),password:H.Yj().required("Password is required!")});return{loading:!1,message:"",schema:e}},computed:{loggedIn(){return this.$store.state.auth.status.loggedIn}},created(){this.loggedIn&&this.$router.push("/profile")},methods:{handleLogin(e){this.loading=!0,this.$store.dispatch("auth/login",e).then((()=>{this.$router.push("/profile")}),(e=>{this.loading=!1,this.message=e.response&&e.response.data&&e.response.data.message||e.message||e.toString()}))}}};const V=(0,y.A)(K,[["render",G]]);var z=V;const Z={class:"row justify-content-center"},ee={class:"col-md-6"},re={class:"card card-container"},se=(0,n.Lk)("img",{id:"profile-img",src:"//ssl.gstatic.com/accounts/ui/avatar_2x.png",class:"profile-img-card"},null,-1),te={key:0},ne={class:"form-group"},ae=(0,n.Lk)("label",{for:"username"},"Username",-1),oe={class:"form-group"},ie=(0,n.Lk)("label",{for:"email"},"Email",-1),ue={class:"form-group"},le=(0,n.Lk)("label",{for:"password"},"Password",-1),ce={class:"form-group"},de=["disabled"],me={class:"spinner-border spinner-border-sm"};function ge(e,r,s,o,i,u){const l=(0,n.g2)("Field"),c=(0,n.g2)("ErrorMessage"),d=(0,n.g2)("Form");return(0,n.uX)(),(0,n.CE)("div",Z,[(0,n.Lk)("div",ee,[(0,n.Lk)("div",re,[se,(0,n.bF)(d,{onSubmit:u.handleRegister,"validation-schema":i.schema},{default:(0,n.k6)((()=>[i.successful?(0,n.Q3)("",!0):((0,n.uX)(),(0,n.CE)("div",te,[(0,n.Lk)("div",ne,[ae,(0,n.bF)(l,{name:"username",type:"text",class:"form-control"}),(0,n.bF)(c,{name:"username",class:"error-feedback"})]),(0,n.Lk)("div",oe,[ie,(0,n.bF)(l,{name:"email",type:"email",class:"form-control"}),(0,n.bF)(c,{name:"email",class:"error-feedback"})]),(0,n.Lk)("div",ue,[le,(0,n.bF)(l,{name:"password",type:"password",class:"form-control"}),(0,n.bF)(c,{name:"password",class:"error-feedback"})]),(0,n.Lk)("div",ce,[(0,n.Lk)("button",{class:"btn btn-primary btn-block",disabled:i.loading},[(0,n.bo)((0,n.Lk)("span",me,null,512),[[t.aG,i.loading]]),(0,n.eW)(" Sign Up ")],8,de)])]))])),_:1},8,["onSubmit","validation-schema"]),i.message?((0,n.uX)(),(0,n.CE)("div",{key:0,class:(0,a.C4)(["alert",i.successful?"alert-success":"alert-danger"])},(0,a.v_)(i.message),3)):(0,n.Q3)("",!0)])])])}var fe={name:"Register",components:{Form:J.lV,Field:J.D0,ErrorMessage:J.Kw},data(){const e=H.Ik().shape({username:H.Yj().required("Username is required!").min(3,"Must be at least 3 characters!").max(20,"Must be maximum 20 characters!"),email:H.Yj().required("Email is required!").email("Email is invalid!").max(50,"Must be maximum 50 characters!"),password:H.Yj().required("Password is required!").min(6,"Must be at least 6 characters!").max(40,"Must be maximum 40 characters!")});return{successful:!1,loading:!1,message:"",schema:e}},computed:{loggedIn(){return this.$store.state.auth.status.loggedIn}},mounted(){this.loggedIn&&this.$router.push("/profile")},methods:{handleRegister(e){this.message="",this.successful=!1,this.loading=!0,this.$store.dispatch("auth/register",e).then((e=>{this.message=e.message,this.successful=!0,this.loading=!1}),(e=>{this.message=e.response&&e.response.data&&e.response.data.message||e.message||e.toString(),this.successful=!1,this.loading=!1}))}}};const pe=(0,y.A)(fe,[["render",ge]]);var he=pe;const ve=()=>s.e(885).then(s.bind(s,2885)),be=()=>s.e(390).then(s.bind(s,2390)),ke=()=>s.e(725).then(s.bind(s,6725)),Le=[{path:"/",name:"home",component:U},{path:"/home",component:U},{path:"/login",component:z},{path:"/register",component:he},{path:"/profile",name:"profile",component:ve},{path:"/admin",name:"admin",component:be},{path:"/user",name:"user",component:ke}],we=(0,S.aE)({history:(0,S.LA)(),routes:Le});we.beforeEach(((e,r,s)=>{const t=["/login","/register","/home"],n=!t.includes(e.path),a=localStorage.getItem("user");n&&!a?s("/login"):s()}));var ye=we,Fe=s(6278),Ee=s(4335);const Se="http://localhost:8080/api/auth/";class Oe{login(e){return Ee.A.post(Se+"signin",{username:e.username,password:e.password}).then((e=>(e.data.accessToken&&localStorage.setItem("user",JSON.stringify(e.data)),e.data)))}logout(){localStorage.removeItem("user")}register(e){return Ee.A.post(Se+"signup",{username:e.username,email:e.email,password:e.password})}}var Ae=new Oe;const je=JSON.parse(localStorage.getItem("user")),Ie=je?{status:{loggedIn:!0},user:je}:{status:{loggedIn:!1},user:null},Ce={namespaced:!0,state:Ie,actions:{login({commit:e},r){return Ae.login(r).then((r=>(e("loginSuccess",r),Promise.resolve(r))),(r=>(e("loginFailure"),Promise.reject(r))))},logout({commit:e}){Ae.logout(),e("logout")},register({commit:e},r){return Ae.register(r).then((r=>(e("registerSuccess"),Promise.resolve(r.data))),(r=>(e("registerFailure"),Promise.reject(r))))}},mutations:{loginSuccess(e,r){e.status.loggedIn=!0,e.user=r},loginFailure(e){e.status.loggedIn=!1,e.user=null},logout(e){e.status.loggedIn=!1,e.user=null},registerSuccess(e){e.status.loggedIn=!1},registerFailure(e){e.status.loggedIn=!1}}},_e=(0,Fe.y$)({modules:{auth:Ce}});var Ue=_e,xe=(s(2754),s(7107)),Pe=s(1273),Me=s(6188);xe.Yv.add(Me.v02,Me.X46,Me.nWR,Me.rhE,Me.GxD),(0,t.Ef)(E).use(ye).use(Ue).component("font-awesome-icon",Pe.gc).mount("#app")},619:function(e,r,s){s.d(r,{A:function(){return i}});var t=s(4335);function n(){let e=JSON.parse(localStorage.getItem("user"));return e&&e.accessToken?{Authorization:"Bearer "+e.accessToken}:{}}const a="http://localhost:8080/api/";class o{getPublicContent(){return t.A.get(a+"all")}getUserBoard(){return t.A.get(a+"user",{headers:n()})}getAdminBoard(){return t.A.get(a+"admin",{headers:n()})}}var i=new o}},r={};function s(t){var n=r[t];if(void 0!==n)return n.exports;var a=r[t]={exports:{}};return e[t].call(a.exports,a,a.exports,s),a.exports}s.m=e,function(){var e=[];s.O=function(r,t,n,a){if(!t){var o=1/0;for(c=0;c<e.length;c++){t=e[c][0],n=e[c][1],a=e[c][2];for(var i=!0,u=0;u<t.length;u++)(!1&a||o>=a)&&Object.keys(s.O).every((function(e){return s.O[e](t[u])}))?t.splice(u--,1):(i=!1,a<o&&(o=a));if(i){e.splice(c--,1);var l=n();void 0!==l&&(r=l)}}return r}a=a||0;for(var c=e.length;c>0&&e[c-1][2]>a;c--)e[c]=e[c-1];e[c]=[t,n,a]}}(),function(){s.n=function(e){var r=e&&e.__esModule?function(){return e["default"]}:function(){return e};return s.d(r,{a:r}),r}}(),function(){s.d=function(e,r){for(var t in r)s.o(r,t)&&!s.o(e,t)&&Object.defineProperty(e,t,{enumerable:!0,get:r[t]})}}(),function(){s.f={},s.e=function(e){return Promise.all(Object.keys(s.f).reduce((function(r,t){return s.f[t](e,r),r}),[]))}}(),function(){s.u=function(e){return"js/"+e+"."+{390:"0d6e02fb",725:"b4518e79",885:"3c51322b"}[e]+".js"}}(),function(){s.miniCssF=function(e){}}(),function(){s.g=function(){if("object"===typeof globalThis)return globalThis;try{return this||new Function("return this")()}catch(e){if("object"===typeof window)return window}}()}(),function(){s.o=function(e,r){return Object.prototype.hasOwnProperty.call(e,r)}}(),function(){var e={},r="frontend:";s.l=function(t,n,a,o){if(e[t])e[t].push(n);else{var i,u;if(void 0!==a)for(var l=document.getElementsByTagName("script"),c=0;c<l.length;c++){var d=l[c];if(d.getAttribute("src")==t||d.getAttribute("data-webpack")==r+a){i=d;break}}i||(u=!0,i=document.createElement("script"),i.charset="utf-8",i.timeout=120,s.nc&&i.setAttribute("nonce",s.nc),i.setAttribute("data-webpack",r+a),i.src=t),e[t]=[n];var m=function(r,s){i.onerror=i.onload=null,clearTimeout(g);var n=e[t];if(delete e[t],i.parentNode&&i.parentNode.removeChild(i),n&&n.forEach((function(e){return e(s)})),r)return r(s)},g=setTimeout(m.bind(null,void 0,{type:"timeout",target:i}),12e4);i.onerror=m.bind(null,i.onerror),i.onload=m.bind(null,i.onload),u&&document.head.appendChild(i)}}}(),function(){s.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})}}(),function(){s.p="/"}(),function(){var e={524:0};s.f.j=function(r,t){var n=s.o(e,r)?e[r]:void 0;if(0!==n)if(n)t.push(n[2]);else{var a=new Promise((function(s,t){n=e[r]=[s,t]}));t.push(n[2]=a);var o=s.p+s.u(r),i=new Error,u=function(t){if(s.o(e,r)&&(n=e[r],0!==n&&(e[r]=void 0),n)){var a=t&&("load"===t.type?"missing":t.type),o=t&&t.target&&t.target.src;i.message="Loading chunk "+r+" failed.\n("+a+": "+o+")",i.name="ChunkLoadError",i.type=a,i.request=o,n[1](i)}};s.l(o,u,"chunk-"+r,r)}},s.O.j=function(r){return 0===e[r]};var r=function(r,t){var n,a,o=t[0],i=t[1],u=t[2],l=0;if(o.some((function(r){return 0!==e[r]}))){for(n in i)s.o(i,n)&&(s.m[n]=i[n]);if(u)var c=u(s)}for(r&&r(t);l<o.length;l++)a=o[l],s.o(e,a)&&e[a]&&e[a][0](),e[a]=0;return s.O(c)},t=self["webpackChunkfrontend"]=self["webpackChunkfrontend"]||[];t.forEach(r.bind(null,0)),t.push=r.bind(null,t.push.bind(t))}();var t=s.O(void 0,[504],(function(){return s(7802)}));t=s.O(t)})();
//# sourceMappingURL=app.f412e625.js.map