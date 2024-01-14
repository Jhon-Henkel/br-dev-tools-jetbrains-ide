package com.github.jhonhenkel.brdevtoolsjetbrainside.toolWindow

import com.github.jhonhenkel.brdevtoolsjetbrainside.services.RandomCnpjService
import com.github.jhonhenkel.brdevtoolsjetbrainside.services.RandomCpfService
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBPanel
import com.intellij.ui.content.ContentFactory
import java.awt.GridLayout
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import javax.swing.JButton

class MyToolWindowFactory : ToolWindowFactory {

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val myToolWindow = BrDevToolsWindow(toolWindow)

        toolWindow.contentManager.addContent(
            ContentFactory.getInstance().createContent(myToolWindow.getContent(), null, false)
        )
    }

    override fun shouldBeAvailable(project: Project) = true

    class BrDevToolsWindow(toolWindow: ToolWindow) {

        private val cpfService = toolWindow.project.service<RandomCpfService>()
        private val cnpjService = toolWindow.project.service<RandomCnpjService>()

        fun getContent() = JBPanel<JBPanel<*>>().apply {

            val panel = JBPanel<JBPanel<*>>()
            panel.setLayout(GridLayout(2,3))
            panel.withPreferredSize(350, 50)

            val randomCpf = JBLabel("")
            panel.add(JButton("CPF").apply {
                addActionListener {
                    randomCpf.text = cpfService.generateRandomCpf()
                }
            })
            panel.add(randomCpf)
            panel.add(JButton("Copiar").apply {
                addActionListener {
                    val cpf = StringSelection(randomCpf.text)
                    val clipboard = Toolkit.getDefaultToolkit().systemClipboard
                    clipboard.setContents(cpf, null)
                }
            })

            val panel2 = JBPanel<JBPanel<*>>()
            panel2.setLayout(GridLayout(2,3))
            panel2.withPreferredSize(350, 50)
            val randomCnpj = JBLabel("")
            panel2.add(JButton("CNPJ").apply {
                addActionListener {
                    randomCnpj.text = cnpjService.generateRandomCnpj()
                }
            })
            panel2.add(randomCnpj)
            panel2.add(JButton("Copiar").apply {
                addActionListener {
                    val cpf = StringSelection(randomCnpj.text)
                    val clipboard = Toolkit.getDefaultToolkit().systemClipboard
                    clipboard.setContents(cpf, null)
                }
            })

            add(panel)
            add(panel2)
        }
    }
}
