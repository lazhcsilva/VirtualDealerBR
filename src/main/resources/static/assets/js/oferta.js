  $('#marcas').focusin(function() {
    var idtipoveiculo = $("#tipoveiculo").val();

    $("#marcas").autocomplete({
      source: function(request, response) {
        $.ajax({
          type: 'GET',
          url: 'https://fipeapi.appspot.com/api/1/' + idtipoveiculo + '/marcas.json',
          dataType: 'jsonp',
          crossDomain: true,
          success: function(data) {
            response($.ui.autocomplete.filter($.map(data, function(item) {
              return {
                label: item.fipe_name,
                id: item.id
              }
            }), request.term))
          },
        });
      },
      minLength: 2,
      //evento de quando você seleciona uma opção   
      select: function(event, ui) {
        //seto a descrição para aparecer para usuario no input text
        $("#marcas").val(ui.item.label);
        //seto o id para ir para seu backend :D
        $("#idmarcas").val(ui.item.id);

        event.preventDefault();
      }
    });
  });

  $('#veiculos').focusin(function() {
    var idtipoveiculo = $("#tipoveiculo").val();
    var idmarcas = $("#idmarcas").val();

    $("#veiculos").autocomplete({
      source: function(request, response) {
        $.ajax({
          type: 'GET',
          url: 'https://fipeapi.appspot.com/api/1/' + idtipoveiculo + '/veiculos/' + idmarcas + '.json',
          dataType: 'jsonp',
          crossDomain: true,
          success: function(data) {
            response($.ui.autocomplete.filter($.map(data, function(item) {
              return {
                label: item.fipe_name,
                id: item.id
              }
            }), request.term))
          },
        });
      },
      minLength: 0,
      //evento de quando você seleciona uma opção   
      select: function(event, ui) {
        //seto a descrição para aparecer para usuario no input text
        $("#veiculos").val(ui.item.label);
        //seto o id para ir para seu backend :D
        $("#idveiculos").val(ui.item.id);

        event.preventDefault();
      }
    });
  });

  $('#modelo').focusin(function() {
    var idtipoveiculo = $("#tipoveiculo").val();
    var idmarcas = $("#idmarcas").val();
    var idmodelo = $("#idveiculos").val();

    $("#modelo").autocomplete({
      source: function(request, response) {
        $.ajax({
          type: 'GET',
          url: 'https://fipeapi.appspot.com/api/1/' + idtipoveiculo + '/veiculo/' + idmarcas + '/' + idmodelo + '.json',
          dataType: 'jsonp',
          crossDomain: true,
          success: function(data) {
            response($.ui.autocomplete.filter($.map(data, function(item) {
              return {
                label: item.name,
                id: item.fipe_codigo
              }
            }), request.term))
          },
        });
      },
      minLength: 0,
      //evento de quando você seleciona uma opção   
      select: function(event, ui) {
        //seto a descrição para aparecer para usuario no input text
        $("#modelo").val(ui.item.label);
        //seto o id para ir para seu backend :D
        $("#idmodelo").val(ui.item.id);

        event.preventDefault();
      }
    });

  });

  $('#modelo').focusout(recuperaDadosFipe);

  function recuperaDadosFipe() {
    var idmarcas = $("#idmarcas").val();
    var idtipoveiculo = $("#tipoveiculo").val();
    var idmodelo = $("#idveiculos").val();
    var idano = $("#idmodelo").val();
    $.ajax({
      type: 'GET',
      url: 'https://fipeapi.appspot.com/api/1/' + idtipoveiculo + '/veiculo/' + idmarcas + '/' + idmodelo + '/' + idano + '.json',
      dataType: 'jsonp',
      crossDomain: true,
      success: function(data) {

        $('#vfipe').val(data.preco);
        $('#idfipe').val(data.fipe_codigo);
        $('#reffipe').val(data.referencia);
      }

    });

  };

$('#tipoveiculo').on('change', function() {
  $("#idmarcas").val('');
  $("#idveiculos").val('');
  $("#marcas").val('');
  $("#veiculos").val('');
  $("#modelo").val('');
  $('#vfipe').val('');
  $('#idfipe').val('');
  $('#reffipe').val('');
});

$("#placa").mask("AAA-9999");

$(document).ready(function() {
    $('#list').click(function(event){event.preventDefault();$('#products .item').addClass('list-group-item');});
    $('#grid').click(function(event){event.preventDefault();$('#products .item').removeClass('list-group-item');$('#products .item').addClass('grid-group-item');});
});

var galleryTop = new Swiper('.gallery-top', {
    spaceBetween: 10,
    navigation: {
      nextEl: '.swiper-button-next',
      prevEl: '.swiper-button-prev',
    },
  });
  var galleryThumbs = new Swiper('.gallery-thumbs', {
    spaceBetween: 10,
    centeredSlides: true,
    slidesPerView: 'auto',
    touchRatio: 0.2,
    slideToClickedSlide: true,
  });
  galleryTop.controller.control = galleryThumbs;
  galleryThumbs.controller.control = galleryTop;
  
$('#meuModal').on('shown.bs.modal', function () {
	$('#meuInput').trigger('focus')
});