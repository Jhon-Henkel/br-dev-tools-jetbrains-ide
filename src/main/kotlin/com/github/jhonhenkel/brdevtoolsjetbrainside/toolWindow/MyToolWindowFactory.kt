package com.github.jhonhenkel.brdevtoolsjetbrainside.toolWindow

import com.github.jhonhenkel.brdevtoolsjetbrainside.services.RandomCnpjService
import com.github.jhonhenkel.brdevtoolsjetbrainside.services.RandomCpfService
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.components.JBPanel
import com.intellij.ui.components.JBTextField
import com.intellij.ui.content.ContentFactory
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel

class MyToolWindowFactory : ToolWindowFactory {

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val myToolWindow = BrDevToolsWindow(toolWindow)

        toolWindow.contentManager.addContent(
            ContentFactory.getInstance().createContent(myToolWindow.getContent(), null, false)
        )
    }

    override fun shouldBeAvailable(project: Project) = true

    class BrDevToolsWindow(toolWindow: ToolWindow) {

        private val defaultItemHeight = 25
        private val defaultLabelWidth = 35
        private val defaultButtonWidth = 75
        private val defaultTextFieldWidth = 150
        private val defaultPanelWidth = 350
        private val cpfService = toolWindow.project.service<RandomCpfService>()
        private val cnpjService = toolWindow.project.service<RandomCnpjService>()

        fun getContent() = JBPanel<JBPanel<*>>().apply {
            add(makeTopPanel())
            add(makeCpfPanel())
            add(makeCnpjPanel())
        }

        private fun makeTopPanel(): JPanel {
            val panel = JPanel(BorderLayout())
            val label = JLabel("Br Dev Tools, make data for Brazilian dev's.")
            label.preferredSize = Dimension(defaultPanelWidth, defaultItemHeight)
            panel.add(label, BorderLayout.PAGE_START)
            return panel
        }

        private fun makeCpfPanel(): JPanel {
            val panel = JBPanel<JBPanel<*>>()

            // column 1
            panel.add(makeDefaultLabel("CPF"))

            // column 2
            val randomCpfField = makeDefaultTextField(cpfService.generateRandomCpf())
            panel.add(randomCpfField)

            // column 3
            val buttonMakeCpf = makeDefaultButton("Make")
            panel.add(buttonMakeCpf.apply {
                addActionListener {
                    randomCpfField.text = cpfService.generateRandomCpf()
                }
            })

            // column 4
            val buttonCopyCpf = makeDefaultButton("Copy")
            panel.add(buttonCopyCpf.apply {
                addActionListener {
                    copyToClipboard(randomCpfField.text)
                }
            })

            return panel
        }

        private fun makeCnpjPanel(): JPanel {
            val panel = JBPanel<JBPanel<*>>()

            // column 1
            panel.add(makeDefaultLabel("CNPJ"))

            // column 2
            val randomCnpjField = makeDefaultTextField(cnpjService.generateRandomCnpj())
            panel.add(randomCnpjField)

            // column 3
            val buttonMakeCnpj = makeDefaultButton("Make")
            panel.add(buttonMakeCnpj.apply {
                addActionListener {
                    randomCnpjField.text = cnpjService.generateRandomCnpj()
                }
            })

            // column 4
            val buttonCopyCnpj = makeDefaultButton("Copy")
            panel.add(buttonCopyCnpj.apply {
                addActionListener {
                    copyToClipboard(randomCnpjField.text)
                }
            })

            return panel
        }

        private fun makeDefaultLabel(text: String): JLabel {
            val label = JLabel(text)
            label.preferredSize = Dimension(defaultLabelWidth, defaultItemHeight)
            return label
        }

        private fun makeDefaultTextField(content: String): JBTextField {
            val field = JBTextField(content)
            field.isEnabled = false
            field.preferredSize = Dimension(defaultTextFieldWidth, defaultItemHeight)
            return field
        }

        private fun makeDefaultButton(text: String): JButton {
            val button = JButton(text)
            button.preferredSize = Dimension(defaultButtonWidth, defaultItemHeight)
            return button
        }

        private fun copyToClipboard(text: String) {
            val item = StringSelection(text)
            val clipboard = Toolkit.getDefaultToolkit().systemClipboard
            clipboard.setContents(item, null)
        }
    }
}
