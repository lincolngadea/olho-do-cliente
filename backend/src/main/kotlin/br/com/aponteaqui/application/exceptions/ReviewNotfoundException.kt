package br.com.aponteaqui.application.exceptions

import java.util.UUID

class ReviewNotfoundException(id: UUID): RuntimeException("Avaliação do id: $id não encontrada")